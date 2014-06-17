package com.self.service.function;

import com.self.service.function.util.MenuFunctionUtil;
import com.self.service.settings.WebSetting;

public class MenuFunction{
	public static String getMainMenuList() {
		return MenuFunctionUtil.getInstance().getMenuList(WebSetting.INTRO_MENU);
	}
	public static String getSubMenuList() {
		return MenuFunctionUtil.getInstance().getMenuList(WebSetting.SUB_MENU);
	}
}
