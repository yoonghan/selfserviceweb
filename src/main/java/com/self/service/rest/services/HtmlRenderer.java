package com.self.service.rest.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.self.service.function.util.ImageFunctionUtil;
import com.self.service.function.util.MenuBtnFunctionUtil;
import com.self.service.function.util.MenuFunctionUtil;

import com.self.service.settings.WebSetting;

@Path("/html")
public class HtmlRenderer{
	
	@GET
	@Path("/mainmenu")
	public String mainmenu(){
		return MenuFunctionUtil.getInstance().getMenuList(WebSetting.INTRO_MENU);
	}
	
	@GET
	@Path("/submenu")
	public String submenu(){
		return MenuFunctionUtil.getInstance().getMenuList(WebSetting.SUB_MENU);
	}
	
	@GET
	@Path("/introBtn")
	public String introBtn(){
		return  MenuBtnFunctionUtil.getInstance().getMenuBtn(WebSetting.INTRO_BTN_MNU);
	}
	
	@GET
	@Produces("application/json")
	@Path("/introImages")
	public String introImages(){
		return ImageFunctionUtil.getInstance().getIntroImage();
	}
}
