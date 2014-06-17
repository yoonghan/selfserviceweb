package com.self.service.function.util;

import static com.self.service.settings.WebSetting.LIST_CLASS;
import static com.self.service.settings.WebSetting.MENU_CLASS;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.self.care.store.jdbi.caches.MenuListCache;
import com.self.care.store.jdbi.entity.EnumType;
import com.self.care.store.jdbi.entity.MenuBean;
import com.self.care.store.jdbi.entity.MenuListBean;
import com.self.service.function.MBeanFunction;
import com.self.service.function.entity.HtmlTagCacheObject;
import com.self.service.function.entity.MenuListObject;
import com.self.service.logging.log.LogUtil;

public class MenuFunctionUtil extends AbstractLocationPathDetector{
	
	private final String CLASS_NAME = "com.self.service.function.MenuFunctionUtil";
	private final HashMap<String, HtmlTagCacheObject> menuMemory = new HashMap<String, HtmlTagCacheObject>(2);

	private final String ORDERLIST = "ul";
	private final String LIST = "li";
	private final int FIRST_INDEX = 0;
	
	
	private static final class Singleton{
		private static final MenuFunctionUtil instance = new MenuFunctionUtil();
	}
	
	private MenuFunctionUtil(){
		
	}
	
	public static MenuFunctionUtil getInstance(){
		return Singleton.instance;
	}
	
	public String getMenuList(final String MENU_GROUP_ID) {

		List<MenuListBean> menuList = null;
		String listReturn = "";

		try {
			menuList = MenuListCache.getInstance().getValue(
					MENU_GROUP_ID);
		} catch (ExecutionException e) {
			LogUtil.getInstance(CLASS_NAME).warn(
					"No records returned for:" + MENU_GROUP_ID);
		}

		if (menuList != null) {
			HtmlTagCacheObject htmlTag = menuMemory.get(MENU_GROUP_ID);
			
			if(htmlTag == null 
					|| htmlTag.getHashCode() != menuList.hashCode()
					|| isServerLocationChanged()){
				
				listReturn = updateMenuMemory(
						htmlTag,
						menuList,
						MENU_GROUP_ID
						);
				
				LogUtil.getInstance(CLASS_NAME).info("Menu created.");
				
			}else{
				listReturn = htmlTag.getHtmlTag();
			}
			
		}

		return listReturn;
	}
	
	//synchronized and let it queue to build the menu.
	private synchronized String updateMenuMemory(
			final HtmlTagCacheObject htmlTag, 
			final List<MenuListBean> menuList, 
			final String type){
		
		//avoid too many update.
		if(htmlTag != null && htmlTag.getHashCode() != menuList.hashCode())
			return htmlTag.getHtmlTag();
		
		String menuTag = createHTMLMenuList(menuList, menuList.get(0).getLevel(), FIRST_INDEX).getStmt();
		HtmlTagCacheObject htco = new HtmlTagCacheObject(menuList.hashCode(), menuTag);
		menuMemory.put(type, htco);
		return menuTag;
	}

	/**
	 * Recursive loop, be careful of infinite loop.
	 * @param menuList
	 * @param currentLevel
	 * @param idx
	 * @return
	 */
	private MenuListObject createHTMLMenuList(final List<MenuListBean> menuList, final Short currentLevel, final int idx) {
		StringBuilder sb = new StringBuilder(200);
		
		if(idx != FIRST_INDEX){
			sb.append("<")
				.append(ORDERLIST)
				.append(" class=\"").append(MENU_CLASS+currentLevel).append("\"")
				.append(">");
		}
		
		int loop=idx;
		for (; loop<menuList.size(); loop++) {
			MenuListBean menu = menuList.get(loop);
			
			Short level = menu.getLevel();
			
			if(currentLevel.equals(level)){
				
				if(idx != loop){//close the index, as the first 1 list element never gets closed.
					sb.append("</").append(LIST).append(">");
				}
				
				sb.append("<")
				.append(LIST)
				.append(" class=\"").append(LIST_CLASS+loop).append("\"")
				.append(">");
				sb.append(createMenu(menu.getMenu()));
				
			}else{
				MenuListObject listObj= createHTMLMenuList(menuList,level,loop);
				loop = listObj.getIndex();
				sb.append(listObj.getStmt());
				sb.append("</").append(LIST).append(">");
			}
		}
		
		if(idx != FIRST_INDEX){//close first index list and order list
			sb.append("</").append(LIST).append(">");
			sb.append("</").append(ORDERLIST).append(">");
		}

		return new MenuListObject(sb.toString(),loop);
	}

	private String createMenu(MenuBean menu) {
		
		EnumType menuType = EnumType.valueOf(menu.getEnumTypeId());
		
		StringBuffer sb = new StringBuffer(100);
		
		String link = menu.getLinkURI();
		
		switch(menuType){
		case IMAGE:
			sb.append("<img src=")
				.append("\"").append(menu.getImageURI()).append("\"")
				.append("/>");
			
			break;
		case TEXT:
			sb.append(menu.getTextDisplay());
			break;
		case EXTERNAL_TEXT_LINK:
			
			if(link != null){
				sb.append("<a href=")
					.append("\"").append(menu.getLinkURI()).append("\"")
					.append(">");
			}
			
			sb.append(menu.getTextDisplay());
			
			if(link != null)
				sb.append("</a>");
			break;
		case INTERNAL_TEXT_LINK:
			
			if(link != null){
				sb.append("<a href=")
					.append(MBeanFunction.getServerLocation()).append("/")
					.append("\"").append(menu.getLinkURI()).append("\"")
					.append(">");
			}
			
			sb.append(menu.getTextDisplay());
			
			if(link != null)
				sb.append("</a>");
			break;
		case IMAGE_EXTERNAL_LINK:

			if(link != null){
				sb.append("<a href=")
					.append("\"").append(menu.getLinkURI()).append("\"")
					.append(">");
			}
			
			sb.append(menu.getTextDisplay());
			
			if(link != null)
				sb.append("</a>");
			break;
		case IMAGE_INTERNAL_LINK:
			link = menu.getLinkURI();

			if(link != null){
				sb.append("<a href=")
					.append("\"")
					.append(MBeanFunction.getServerLocation()).append("/")
					.append(menu.getLinkURI())
					.append("\"")
					.append(">");
			}
			
			sb.append("<img src=")
				.append("\"").append(menu.getImageURI()).append("\"")
				.append("/>");
			
			if(link != null)
				sb.append("</a>");
			break;
		}
		return sb.toString();
	}
}
