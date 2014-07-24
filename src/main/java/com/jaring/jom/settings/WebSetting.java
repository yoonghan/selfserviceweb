package com.jaring.jom.settings;

public interface WebSetting {
	public final static String INTRO_CATEGORY = "1";
	public final static String INTRO_MENU = "1";
	public final static String SUB_MENU = "2";
	public final static String INTRO_BTN_MNU = "80";
	
	public final static String MENU_CLASS = "menu_";
	public final static String LIST_CLASS = "list_";
	
	public final static String IMAGE_LOCATION = "/cache/img/";
	
	public final static String CSS_MENU_TITLE="JOM Jaring";
	public final static String CSS_MENU_CLASS="menuTitle";
	
	public final static String APPLICATION_NAME = "JomJaring/1.0";
	public final static String OAUTH_CALLBACK_NAME = "gmailCallback";

	public final static String CALLBACK_URL = new CommonProperties().getCallBackURL();
	public final static String CALLBACK_REDIRECT_URL = new CommonProperties().getCallBackRedirectURL();
}
