package com.jaring.jom.settings;

import java.io.IOException;
import java.util.Properties;

import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;
import com.jaring.jom.util.common.PropertyLoaderUtil;

import static com.jaring.jom.util.impl.PropertyFiles.*;

/**
 * Make this class to load every time, but do not store it into memory.
 * Not worth.
 * @author yoong.han
 */
public class CommonProperties{
	
	private Log  log = LogFactory.getLogger(CommonProperties.class.getName());
	
	private String callbackURL;
	private String callbackRedirectURL;
	
	CommonProperties(){
		PropertyLoaderUtil propUtil = new PropertyLoaderUtil();
		try {
			Properties prop = new Properties();
			prop.load(propUtil.fileLoader(COMMON_PROP));
			
			map(prop);
			
		} catch (ClassNotFoundException | IOException e) {
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
