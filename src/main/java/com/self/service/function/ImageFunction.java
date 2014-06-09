package com.self.service.function;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.self.care.store.jdbi.caches.ImageCategoryCache;
import com.self.care.store.jdbi.entity.ImageBean;
import com.self.service.settings.WebSetting;
import com.self.service.util.log.LogUtil;

public class ImageFunction {

	private final static String CLASS_NAME = "com.self.service.function.ImageFunction";
	
	public static List<ImageBean> getIntroImages(){
		
		List<ImageBean> imagebeans = null;
		
		try {
			imagebeans =  ImageCategoryCache.getInstance().getValue(WebSetting.INTRO_CATEGORY);
		} catch (ExecutionException e) {
			LogUtil.getInstance(CLASS_NAME).info("No records returned for:" + WebSetting.INTRO_CATEGORY);
			imagebeans = new ArrayList<ImageBean>(0);
		}
		return imagebeans;
	}
}
