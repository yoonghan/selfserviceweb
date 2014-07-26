package com.jaring.jom.function.util;

import java.util.concurrent.ExecutionException;

import com.google.common.collect.ImmutableList;
import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;
import com.jaring.jom.store.jdbi.caches.DBCache;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableCustomList;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableImageBean;

import static com.jaring.jom.settings.WebSetting.*;

public class ImageFunctionUtil extends AbstractCacheFunction<ImmutableCustomList<ImmutableImageBean>, String>{
	
	private final static int ESTIMATED_MEMORY_USE=1;
	private final String FADE_TIME="1000";
	
	private final Log log = LogFactory.getLogger(getClass().getName());
	private final String CLASS_NAME = "com.jaring.jom.function.ImageFunctionUtil";
	
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
			ImmutableCustomList<ImmutableImageBean> imageBean = DBCache.INSTANCE.getImageCategory().getValue(INTRO_CATEGORY);
			introImageHtmlCode = getCache(CLASS_NAME, imageBean, INTRO_CATEGORY);
		} catch (ExecutionException e) {
			log.warn("No records returned for:" + INTRO_CATEGORY);
		}
		
		return introImageHtmlCode;
	}
	
	protected String contructHtmlCode(ImmutableCustomList<ImmutableImageBean> immutableImageBean) {
		ImmutableList<ImmutableImageBean> imageBean = immutableImageBean.getList();
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
