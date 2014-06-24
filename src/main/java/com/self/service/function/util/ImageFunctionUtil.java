package com.self.service.function.util;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.self.care.store.jdbi.caches.ImageCategoryCache;
import com.self.care.store.jdbi.entity.ImageBean;
import com.self.service.function.entity.ImageListObject;
import com.self.service.logging.log.LogUtil;
import com.self.service.settings.WebSetting;

public class ImageFunctionUtil extends AbstractCacheFunction<List<ImageBean>, ImageListObject>{
	
	private final static int ESTIMATED_MEMORY_USE=1;
	private final String FADE_TIME="fadeTime";
	
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
	
	public ImageListObject getIntroImage(){
		
		ImageListObject introImageHtmlCode;
		
		try {
			List<ImageBean> imageBean = ImageCategoryCache.getInstance().getValue(WebSetting.INTRO_CATEGORY);
			introImageHtmlCode = getCache(CLASS_NAME, imageBean, WebSetting.INTRO_CATEGORY);
		} catch (ExecutionException e) {
			LogUtil.getInstance(CLASS_NAME).warn("No records returned for:" + WebSetting.INTRO_CATEGORY);
			introImageHtmlCode=new ImageListObject("\"\"","\"\"");
		}
		
		return introImageHtmlCode;
	}
	
	protected ImageListObject contructHtmlCode(List<ImageBean> imagebean) {
		StringBuilder sbImageSrc = new StringBuilder(500);
		StringBuilder sbImageDesc = new StringBuilder(500);
		String serverLocation = getServerLocation();
		for(ImageBean image: imagebean){
			sbImageSrc.append("{src:'")
					.append(serverLocation)
					.append(WebSetting.IMAGE_LOCATION)
					.append(image.getURI())
				.append("', fade:").append(FADE_TIME).append("}").append(",");
			sbImageDesc.append("\"").append(replaceSpecialChar(image.getName())).append("\"").append(",");
		}
		if(imagebean.size() > 1){
			//remove last comma.
			sbImageSrc.deleteCharAt(sbImageSrc.length()-1);
			sbImageDesc.deleteCharAt(sbImageDesc.length()-1);
		}
		
		ImageListObject imageListObject = new ImageListObject(sbImageSrc.toString(), sbImageDesc.toString());
		
		return imageListObject;
	}

	private String replaceSpecialChar(String name) {
		return name.replaceAll("\"", "\\\""); //escape double quote to \"
	}
}
