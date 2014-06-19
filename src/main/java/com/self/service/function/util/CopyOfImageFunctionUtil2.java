package com.self.service.function.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.ConcurrentHashMap;

import com.self.care.store.jdbi.caches.ImageCategoryCache;
import com.self.care.store.jdbi.entity.ImageBean;
import com.self.service.function.entity.HtmlTagCacheObject;
import com.self.service.logging.log.LogUtil;
import com.self.service.settings.WebSetting;

public class CopyOfImageFunctionUtil2 {
	
	private final String CLASS_NAME = "com.self.service.function.ImageFunctionUtil";
	private final ConcurrentHashMap<String, HtmlTagCacheObject> imageMemory = new ConcurrentHashMap<String, HtmlTagCacheObject>();
	private final String CSS_CLASS="swiper-slide";
	
	private static final class Singleton{
		private static final CopyOfImageFunctionUtil2 instance = new CopyOfImageFunctionUtil2();
	}
	
	private CopyOfImageFunctionUtil2(){
		
	}
	
	public static CopyOfImageFunctionUtil2 getInstance(){
		return Singleton.instance;
	}
	
	public String getIntroImage(){
		
		List<ImageBean> imagebeans = null;
		
		String imageList = "";
		
		try {
			imagebeans =  ImageCategoryCache.getInstance().getValue(WebSetting.INTRO_CATEGORY);
			if(imagebeans != null){
				
				HtmlTagCacheObject cacheObj  = 
										(HtmlTagCacheObject)imageMemory.get(WebSetting.INTRO_CATEGORY);
				
				if(cacheObj == null 
						|| cacheObj.getHashCode() != imagebeans.hashCode()){
					imageList = createHtmlTag(imagebeans, cacheObj, WebSetting.INTRO_CATEGORY);
					LogUtil.getInstance(CLASS_NAME).info("Image created.");
				}else{
					imageList = cacheObj.getHtmlTag();
				}
			}
		} catch (ExecutionException e) {
			LogUtil.getInstance(CLASS_NAME).warn("No records returned for:" + WebSetting.INTRO_CATEGORY);
			imagebeans = new ArrayList<ImageBean>(0);
		}
		return imageList;
	}
	
	private synchronized String createHtmlTag(
			final List<ImageBean> imagelist,
			final HtmlTagCacheObject htmlTag,
			final String type
			){
		
		//avoid too many update.
		if(htmlTag != null && htmlTag.getHashCode() == imagelist.hashCode())
			return htmlTag.getHtmlTag();
		
		String imageTag = contructImage(imagelist);
		imageMemory.put(type, new HtmlTagCacheObject(imagelist.hashCode(), imageTag));
		return imageTag;
	}

	private String contructImage(List<ImageBean> imagebean) {
		StringBuilder sb = new StringBuilder(500);
		String serverLocation = null;
		for(ImageBean image: imagebean){
			sb.append("<div class=\"").append(CSS_CLASS).append("\">")
				.append("<img src=\"")
					.append(serverLocation)
					.append(WebSetting.IMAGE_LOCATION)
					.append(image.getURI())
				.append("\">")
				.append("</div>");
		}
		
		return sb.toString();
	}
}
