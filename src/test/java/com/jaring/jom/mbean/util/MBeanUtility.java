package com.jaring.jom.mbean.util;

import com.jaring.jom.mbean.util.MBeanUtility;

/**
 * Mock static class.
 * @author yoong.han
 */
public class MBeanUtility {
	
	private static final class Singleton{
		private static final MBeanUtility instance = new MBeanUtility();
	}
	
	private MBeanUtility(){
	}
	
	public static MBeanUtility getInstance(){
		return Singleton.instance;
	}
	
	public String getActiveServer(){
		return "test server";
	}
}
