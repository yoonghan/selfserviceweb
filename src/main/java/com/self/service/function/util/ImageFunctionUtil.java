package com.self.service.function.util;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.self.care.store.jdbi.caches.ImageCategoryCache;
import com.self.care.store.jdbi.entity.ImageBean;
import com.self.service.logging.log.LogUtil;
import com.self.service.settings.WebSetting;

public class ImageFunctionUtil extends AbstractCacheFunction<List<ImageBean>>{
	
	private final static int ESTIMATED_MEMORY_USE=1;
	
	private final String CLASS_NAME = "com.self.service.function.ImageFunctionUtil";
	private final String CSS_CLASS="swiper-slide";
	
	private static final class Singleton{
		private static final ImageFunctionUtil instance = new ImageFunctionUtil();
	}
	
	private ImageFunctionUtil(){
		super(ESTIMATED_MEMORY_USE);
	}
	
	public static ImageFunctionUtil getInstance(){
		return Singleton.instance;
	}
	
	public String getIntroImage(){
		
		String introImageHtmlCode = "";
		
		try {
			List<ImageBean> imageBean = ImageCategoryCache.getInstance().getValue(WebSetting.INTRO_CATEGORY);
			introImageHtmlCode = getCache(CLASS_NAME, imageBean, WebSetting.INTRO_CATEGORY);
		} catch (ExecutionException e) {
			LogUtil.getInstance(CLASS_NAME).warn("No records returned for:" + WebSetting.INTRO_CATEGORY);
		}
		
		return introImageHtmlCode;
	}
	
	protected String contructHtmlCode(List<ImageBean> imagebean) {
		StringBuilder sb = new StringBuilder(500);
		String serverLocation = getServerLocation();
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
