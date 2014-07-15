package com.self.service.function.util;

import java.util.List;
import java.util.concurrent.ExecutionException;


//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import com.self.care.store.jdbi.caches.MenuListCache;
//import com.self.care.store.jdbi.entity.EnumType;
//import com.self.care.store.jdbi.entity.MenuBean;
import com.self.care.store.jdbi.entity.MenuListBean;
import com.self.service.logging.impl.Log;
import com.self.service.logging.log.LogFactory;
//import com.self.service.function.entity.MenuListObject;
//import com.self.service.settings.WebSetting;
import com.self.service.util.gson.CustomGson;

public class MenuFunctionUtil extends AbstractCacheFunction<List<MenuListBean>, String>{
	
	private final String CLASS_NAME = "com.self.service.function.MenuFunctionUtil";
	private final Log log = LogFactory.getLogger(CLASS_NAME);
	
//	private final String ORDERLIST = "ul";
//	private final String LIST = "li";
//	private final int FIRST_INDEX = 0;
	
	
	private static final class Singleton{
		private static final MenuFunctionUtil instance = new MenuFunctionUtil();
	}
	
	private MenuFunctionUtil(){
		super(2);
	}
	
	public static MenuFunctionUtil getInstance(){
		return Singleton.instance;
	}
	
	public String getMenuList(final String MENU_GROUP_ID) {

		String listReturn = "";

		try {
			List<MenuListBean> menuBean = MenuListCache.getInstance().getValue(MENU_GROUP_ID);
			listReturn = getCache(CLASS_NAME, menuBean, MENU_GROUP_ID);
		} catch (ExecutionException e) {
			log.warn("No records returned for:" + MENU_GROUP_ID);
		}

		return listReturn;
	}
	
	@Override
	protected String contructHtmlCode(List<MenuListBean> menuList) {
		return CustomGson.toGson(menuList);
	}

//	/**
//	 * Recursive loop, be careful of infinite loop.
//	 * @param menuList
//	 * @param currentLevel
//	 * @param idx
//	 * @return
//	 */
//	private MenuListObject createHTMLMenuList(final List<MenuListBean> menuList, final Short currentLevel, final int idx) {
//		StringBuilder sb = new StringBuilder(200);
//		
//		sb.append("<").append(ORDERLIST).append(">");
//
//		if(currentLevel==FIRST_INDEX){
//			sb.append(titleKey());
//		}
//		
//		int loop=idx;
//		for (; loop<menuList.size(); loop++) {
//			MenuListBean menu = menuList.get(loop);
//			
//			Short level = menu.getLevel();
//			
//			if(currentLevel.equals(level)){
//				
//				if(idx != loop){//close the index, as the first 1 list element never gets closed.
//					sb.append("</").append(LIST).append(">");
//				}
//				
//				sb.append("<").append(LIST).append(">");
//				sb.append(createMenu(menu.getMenu()));
//				
//			}else{
//				MenuListObject listObj= createHTMLMenuList(menuList,level,loop);
//				loop = listObj.getIndex();
//				sb.append(listObj.getStmt());
//			}
//		}
//		
//		sb.append("</").append(LIST).append(">");
//		sb.append("</").append(ORDERLIST).append(">");
//
//		return new MenuListObject(sb.toString(),loop);
//	}
//
//	private String titleKey() {
//		return String.format("<%s class=\"%s\">%s</%s>", LIST,WebSetting.CSS_MENU_CLASS,WebSetting.CSS_MENU_TITLE,LIST);
//	}
//
//	private String createMenu(MenuBean menu) {
//		
//		EnumType menuType = EnumType.valueOf(menu.getEnumTypeId());
//		
//		StringBuffer sb = new StringBuffer(100);
//		
//		String link = menu.getLinkURI();
//		
//		switch(menuType){
//		case IMAGE:
//			sb.append("<img src=")
//				.append("\"")
//				.append(getServerLocation())
//				.append(WebSetting.IMAGE_LOCATION)
//				.append(menu.getImageURI()).append("\"")
//				.append("/>");
//			
//			break;
//		case TEXT:
//			sb.append(menu.getTextDisplay());
//			break;
//		case EXTERNAL_TEXT_LINK:
//			
//			if(link != null){
//				sb.append("<a href=")
//					.append("\"").append(menu.getLinkURI()).append("\"")
//					.append(">");
//			}
//			
//			sb.append(menu.getTextDisplay());
//			
//			if(link != null)
//				sb.append("</a>");
//			break;
//		case INTERNAL_TEXT_LINK:
//			
//			if(link != null){
//				String constructlink = menu.getLinkURI();
//				sb.append("<a href=")
//					.append("\"")
//					.append(getServerLocation())
//					.append(constructlink.startsWith("/")?
//							constructlink:
//							"/"+constructlink).append("\"")
//					.append(">");
//			}
//			
//			sb.append(menu.getTextDisplay());
//			
//			if(link != null)
//				sb.append("</a>");
//			break;
//		case IMAGE_EXTERNAL_LINK:
//
//			if(link != null){
//				sb.append("<a href=")
//					.append("\"").append(menu.getLinkURI()).append("\"")
//					.append(">");
//			}
//			
//			sb.append(menu.getTextDisplay());
//			
//			if(link != null)
//				sb.append("</a>");
//			break;
//		case IMAGE_INTERNAL_LINK:
//			link = menu.getLinkURI();
//
//			if(link != null){
//				sb.append("<a href=")
//					.append("\"")
//					.append(getServerLocation())
//					.append(WebSetting.IMAGE_LOCATION)
//					.append(menu.getLinkURI())
//					.append("\"")
//					.append(">");
//			}
//			
//			sb.append("<img src=")
//				.append("\"").append(menu.getImageURI()).append("\"")
//				.append("/>");
//			
//			if(link != null)
//				sb.append("</a>");
//			break;
//		}
//		return sb.toString();
//	}
}
