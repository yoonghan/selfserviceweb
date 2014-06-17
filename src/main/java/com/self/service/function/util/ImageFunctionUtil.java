package com.self.service.function.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.self.care.store.jdbi.caches.ImageCategoryCache;
import com.self.care.store.jdbi.entity.ImageBean;
import com.self.service.function.MBeanFunction;
import com.self.service.function.entity.HtmlTagCacheObject;
import com.self.service.logging.log.LogUtil;
import com.self.service.settings.WebSetting;

public class ImageFunctionUtil extends AbstractLocationPathDetector{
	
	private final String CLASS_NAME = "com.self.service.function.ImageFunctionUtil";
	private final HashMap<String, HtmlTagCacheObject> imageMemory = new HashMap<String, HtmlTagCacheObject>(1);
	private final String CSS_CLASS="swiper-slide";
	
	private static final class Singleton{
		private static final ImageFunctionUtil instance = new ImageFunctionUtil();
	}
	
	private ImageFunctionUtil(){
		
	}
	
	public static ImageFunctionUtil getInstance(){
		return Singleton.instance;
	}
	
	public String getIntroImage(){
		
		List<ImageBean> imagebeans = null;
		
		String imageList = "";
		
		try {
			imagebeans =  ImageCategoryCache.getInstance().getValue(WebSetting.INTRO_CATEGORY);
			if(imagebeans != null){
				
				HtmlTagCacheObject cacheObj  = imageMemory.get(WebSetting.INTRO_CATEGORY);
				
				if(cacheObj == null 
						|| cacheObj.getHashCode() != imagebeans.hashCode()
						|| isServerLocationChanged()){
					imageList = createHtmlTag(imagebeans, cacheObj, WebSetting.INTRO_CATEGORY);
				}
			}
		} catch (ExecutionException e) {
			LogUtil.getInstance(CLASS_NAME).warn("No records returned for:" + WebSetting.INTRO_CATEGORY);
			imagebeans = new ArrayList<ImageBean>(0);
		}
		return imageList;
	}
	
	private synchronized String createHtmlTag(
			final List<ImageBean> imagebean,
			final HtmlTagCacheObject cacheObj,
			final String type
			){
		
		//do a duplicate checking as synchronized is queued.
		if(cacheObj != null && cacheObj.getHashCode() == imagebean.hashCode()){
			return cacheObj.getHtmlTag();
		}
		
		String htmlTag = contructImage(imagebean);
		imageMemory.put(type, new HtmlTagCacheObject(imagebean.hashCode(), htmlTag));
		return htmlTag;
	}

	private String contructImage(List<ImageBean> imagebean) {
		StringBuilder sb = new StringBuilder(500);
		for(ImageBean image: imagebean){
			sb.append("<div class=\"").append(CSS_CLASS).append("\"")
				.append("<img src=\"")
					.append(MBeanFunction.getServerLocation())
					.append(image.getURI())
				.append("\">")
				.append("</div>");
		}
		
		return sb.toString();
	}
}
