package com.jaring.jom.rest.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.jaring.jom.function.util.ImageFunctionUtil;
import com.jaring.jom.function.util.MenuBtnFunctionUtil;
import com.jaring.jom.function.util.MenuFunctionUtil;
import com.jaring.jom.settings.WebSetting;

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
