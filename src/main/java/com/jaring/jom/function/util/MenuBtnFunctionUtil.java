package com.jaring.jom.function.util;

import java.util.concurrent.ExecutionException;

import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;
import com.jaring.jom.store.jdbi.caches.DBCache;
import com.jaring.jom.store.jdbi.entity.immutable.ImmutableMenuBean;
import com.jaring.jom.util.gson.CustomGson;

public class MenuBtnFunctionUtil extends AbstractCacheFunction<ImmutableMenuBean, String>{
	
	private final String CLASS_NAME = "com.jaring.jom.function.MenuBtnFunctionUtil";
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
			ImmutableMenuBean menuBean = DBCache.INSTANCE.getMenu().getValue(BTN_ID);
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
