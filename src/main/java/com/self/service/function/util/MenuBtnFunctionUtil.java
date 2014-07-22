package com.self.service.function.util;

import java.util.concurrent.ExecutionException;

import com.self.care.store.jdbi.caches.MenuCache;
import com.self.care.store.jdbi.entity.immutable.ImmutableMenuBean;
import com.self.service.logging.impl.Log;
import com.self.service.logging.log.LogFactory;
import com.self.service.util.gson.CustomGson;

public class MenuBtnFunctionUtil extends AbstractCacheFunction<ImmutableMenuBean, String>{
	
	private final String CLASS_NAME = "com.self.service.function.MenuBtnFunctionUtil";
	private final Log log = LogFactory.getLogger(CLASS_NAME);
		
	private static final class Singleton{
		private static final MenuBtnFunctionUtil instance = new MenuBtnFunctionUtil();
	}
	
	private MenuBtnFunctionUtil(){
		super(1);
	}
	
	public static MenuBtnFunctionUtil getInstance(){
		return Singleton.instance;
	}
	
	public String getMenuBtn(final String BTN_ID) {

		String htmlBtn = "";

		try {
			ImmutableMenuBean menuBean = MenuCache.getInstance().getValue(BTN_ID);
			htmlBtn = getCache(CLASS_NAME, menuBean, BTN_ID);
		} catch (ExecutionException e) {
			log.warn("No records returned for:" + BTN_ID);
		}

		return htmlBtn;
	}
	
	protected String contructHtmlCode(ImmutableMenuBean menuBean) {
		return CustomGson.toGson(menuBean);
	}

}
