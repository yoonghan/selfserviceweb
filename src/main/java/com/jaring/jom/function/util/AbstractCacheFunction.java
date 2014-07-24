package com.jaring.jom.function.util;

import java.util.concurrent.ConcurrentHashMap;

import com.jaring.jom.function.entity.HtmlTagCacheObject;
import com.jaring.jom.logging.log.LogFactory;

public abstract class AbstractCacheFunction<T, V> {
	
	private final ConcurrentHashMap<String, HtmlTagCacheObject<V>> memory;
	
	protected AbstractCacheFunction(final int estimatedMemoryExpansion){
		memory = new ConcurrentHashMap<String, HtmlTagCacheObject<V>>(estimatedMemoryExpansion);
	}

	protected V getCache(String CLASS_NAME, T cacheBean, String key){
		V htmlTag = null;
		
		if(cacheBean != null){
			HtmlTagCacheObject<V> cacheObj  = (HtmlTagCacheObject<V>)memory.get(key);
	
			if(cacheObj == null 
					|| cacheObj.getHashCode() != cacheBean.hashCode()){
				htmlTag = createHtmlTag(cacheBean, cacheObj, key);
				LogFactory.getLogger(CLASS_NAME).info("Object created.");
			}else{
				htmlTag = cacheObj.getHtmlTag();
			}
		}
		
		return htmlTag;
	}
	
	private synchronized V createHtmlTag(
			final T cacheBean,
			final HtmlTagCacheObject<V> htmlTag,
			final String type
			){
		
		//avoid too many update.
		if(htmlTag != null && htmlTag.getHashCode() == cacheBean.hashCode())
			return htmlTag.getHtmlTag();
		
		V htmlCode = contructHtmlCode(cacheBean);
		memory.put(type, new HtmlTagCacheObject<V>(cacheBean.hashCode(), htmlCode));
		return htmlCode;
	}
	
	protected abstract V contructHtmlCode(T imagebean);
}
