package com.self.service.function.util;

import com.self.service.mbean.util.MBeanUtility;

public class AbstractLocationPathDetector {
	
	private String currentLocation = "";
	
	protected boolean isServerLocationChanged(){
		boolean change =  (false ==  MBeanUtility.getInstance().getActiveServer().equals(currentLocation));
		if(change == true)
			currentLocation = MBeanUtility.getInstance().getActiveServer();
		return change;
	}
	
}
