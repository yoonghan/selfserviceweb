package com.self.service.function.util;

import java.util.concurrent.ConcurrentHashMap;

import com.self.service.function.entity.HtmlTagCacheObject;
import com.self.service.logging.log.LogUtil;
import com.self.service.mbean.util.MBeanUtility;

public abstract class AbstractCacheFunction<T> {
	
	private final ConcurrentHashMap<String, HtmlTagCacheObject> memory;
	
	private String currentLocation = MBeanUtility.getInstance().getActiveServer();
	
	protected boolean isServerLocationChanged(){
		boolean change =  (false == MBeanUtility.getInstance().getActiveServer().equals(currentLocation));
		if(change == true)
			currentLocation = MBeanUtility.getInstance().getActiveServer();
		return change;
	}
	
	protected String getServerLocation(){
		return MBeanUtility.getInstance().getActiveServer();
	}
	
	protected AbstractCacheFunction(final int estimatedMemoryExpansion){
		memory = new ConcurrentHashMap<String, HtmlTagCacheObject>(estimatedMemoryExpansion);
	}
	

	protected String getCache(String CLASS_NAME, T cacheBean, String key){
		String htmlTag = null;
		
		if(cacheBean != null){
			
			HtmlTagCacheObject cacheObj  = isServerLocationChanged()?
									null:
									(HtmlTagCacheObject)memory.get(key);
			
			if(cacheObj == null 
					|| cacheObj.getHashCode() != cacheBean.hashCode()){
				htmlTag = createHtmlTag(cacheBean, cacheObj, key);
				LogUtil.getInstance(CLASS_NAME).info("Object created.");
			}else{
				htmlTag = cacheObj.getHtmlTag();
			}
		}
		
		return htmlTag;
	}
	
	private synchronized String createHtmlTag(
			final T cacheBean,
			final HtmlTagCacheObject htmlTag,
			final String type
			){
		
		//avoid too many update.
		if(htmlTag != null && htmlTag.getHashCode() == cacheBean.hashCode())
			return htmlTag.getHtmlTag();
		
		String htmlCode = contructHtmlCode(cacheBean);
		memory.put(type, new HtmlTagCacheObject(cacheBean.hashCode(), htmlCode));
		return htmlCode;
	}
	
	protected abstract String contructHtmlCode(T imagebean);
}
