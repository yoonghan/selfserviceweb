package com.self.service.function;

import com.self.service.mbean.util.MBeanUtility;

public class MBeanFunction {
	//private final static String CLASS_NAME = "com.self.service.function.MBeanFunction";
	
	public static String getServerLocation(){
		return MBeanUtility.getInstance().getActiveServer();
	}
}
