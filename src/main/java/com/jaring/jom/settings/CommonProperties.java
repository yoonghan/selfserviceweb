package com.jaring.jom.settings;

import java.io.IOException;
import java.util.Properties;

import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;
import com.jaring.jom.util.common.PropertyLoaderUtil;

/**
 * Make this class to load every time, but do not store it into memory.
 * Not worth.
 * @author yoong.han
 */
public class CommonProperties{
	
	private Log  log = LogFactory.getLogger(CommonProperties.class.getName());
	
	private final String COMMON_PROP = "common.properties";
	private String callbackURL;
	private String callbackRedirectURL;
	
	CommonProperties(){
		try {
			Properties prop = new Properties();
			prop.load(PropertyLoaderUtil.fileLoader(COMMON_PROP));
			
			map(prop);
			
		} catch (IOException e) {
			log.error("File not found:",e);
		}
	}
	
	public void map(java.util.Properties property) {
		this.callbackURL = property.getProperty("callbackURL");
		this.callbackRedirectURL = property.getProperty("callbackRedirectURL");
		
		if(callbackURL == null){
			log.error("callbackURL have to be defined in "+COMMON_PROP+" file");
		}
		if(callbackRedirectURL == null){
			log.error("callbackRedirectURL have to be defined in "+COMMON_PROP+" file");
		}
	};
	
	public String getCallBackURL(){
		return callbackURL;
	}
	
	public String getCallBackRedirectURL(){
		return callbackRedirectURL;
	}
}
