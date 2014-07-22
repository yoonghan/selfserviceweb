package com.self.service.function.util;

import java.util.concurrent.ExecutionException;

import com.google.common.collect.ImmutableList;
import com.self.care.store.jdbi.caches.ImageCategoryCache;
import com.self.care.store.jdbi.entity.immutable.ImmutableImageBean;
import com.self.care.store.jdbi.entity.immutable.ImmutableImageList;
import com.self.service.logging.impl.Log;
import com.self.service.logging.log.LogFactory;

import static com.self.service.settings.WebSetting.*;

public class ImageFunctionUtil extends AbstractCacheFunction<ImmutableImageList, String>{
	
	private final static int ESTIMATED_MEMORY_USE=1;
	private final String FADE_TIME="1000";
	
	private final Log log = LogFactory.getLogger(getClass().getName());
	private final String CLASS_NAME = "com.self.service.function.ImageFunctionUtil";
	
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
		
		String introImageHtmlCode="";
		
		try {
			ImmutableImageList imageBean = ImageCategoryCache.getInstance().getValue(INTRO_CATEGORY);
			introImageHtmlCode = getCache(CLASS_NAME, imageBean, INTRO_CATEGORY);
		} catch (ExecutionException e) {
			log.warn("No records returned for:" + INTRO_CATEGORY);
		}
		
		return introImageHtmlCode;
	}
	
	protected String contructHtmlCode(ImmutableImageList immutableImageBean) {
		ImmutableList<ImmutableImageBean> imageBean = immutableImageBean.getArrayObject();
		StringBuilder sbImageSrc = new StringBuilder(500);
		sbImageSrc.append("{\"backgrounds\":[");
		for(ImmutableImageBean image: imageBean){
			sbImageSrc.append("{\"src\":\"")
					.append(IMAGE_LOCATION)
					.append(image.getURI())
				.append("\", \"fade\":").append(FADE_TIME)
//				.append(", load:function() {$(\"note\").text(\")")
//				.append(replaceSpecialChar(image.getName()))
//				.append("\");}")
				.append("}").append(",");
			
		}
		if(imageBean.size() > 1){
			//remove last comma.
			sbImageSrc.deleteCharAt(sbImageSrc.length()-1);
		}
		
		sbImageSrc.append("]}");
		return sbImageSrc.toString();
	}
}
