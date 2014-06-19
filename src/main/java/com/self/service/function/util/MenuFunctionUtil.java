package com.self.service.function.util;

import static com.self.service.settings.WebSetting.LIST_CLASS;
import static com.self.service.settings.WebSetting.MENU_CLASS;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.self.care.store.jdbi.caches.MenuListCache;
import com.self.care.store.jdbi.entity.EnumType;
import com.self.care.store.jdbi.entity.MenuBean;
import com.self.care.store.jdbi.entity.MenuListBean;
import com.self.service.function.entity.MenuListObject;
import com.self.service.logging.log.LogUtil;
import com.self.service.settings.WebSetting;

public class MenuFunctionUtil extends AbstractCacheFunction<List<MenuListBean>>{
	
	private final String CLASS_NAME = "com.self.service.function.MenuFunctionUtil";

	private final String ORDERLIST = "ul";
	private final String LIST = "li";
	private final int FIRST_INDEX = 0;
	
	
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
			LogUtil.getInstance(CLASS_NAME).warn("No records returned for:" + MENU_GROUP_ID);
		}

		return listReturn;
	}
	
	protected String contructHtmlCode(List<MenuListBean> menuList) {
		return createHTMLMenuList(menuList, menuList.get(0).getLevel(), FIRST_INDEX).getStmt();
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
				.append("\"")
				.append(getServerLocation())
				.append(WebSetting.IMAGE_LOCATION)
				.append(menu.getImageURI()).append("\"")
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
				String constructlink = menu.getLinkURI();
				sb.append("<a href=")
					.append("\"")
					.append(getServerLocation())
					.append(constructlink.startsWith("/")?
							constructlink:
							"/"+constructlink).append("\"")
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
					.append(getServerLocation())
					.append(WebSetting.IMAGE_LOCATION)
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
