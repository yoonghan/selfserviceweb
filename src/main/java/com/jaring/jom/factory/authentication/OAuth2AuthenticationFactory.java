package com.jaring.jom.factory.authentication;

import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;
import com.jaring.jom.util.authentication.facebook.FacebookAuthentication;
import com.jaring.jom.util.authentication.google.GoogleAuthentication;
import com.jaring.jom.util.impl.IOAuthImpl;

import static com.jaring.jom.settings.WebSetting.*;

public class OAuth2AuthenticationFactory {
	
	private static Log log = LogFactory.getLogger("com.jaring.jom.function.util.OAuth2AuthenticationFactory");
		
	private static GoogleAuthentication googleAuthenticate = null;
	private static FacebookAuthentication facebookAuthenticate = null;
	
	public static IOAuthImpl getOAuth(EnumAuthenticationType type){
		
		IOAuthImpl oauth = null;
		
		switch(type){
			case GOOGLE:
				oauth = getGMailAuthentication();
				break;
			case FACEBOOK:
				oauth = getFacebookAuthentication();
				break;
		}
		
		return oauth;
	}
	
	private static IOAuthImpl getGMailAuthentication(){
		if(googleAuthenticate == null){
			try{
				googleAuthenticate = new GoogleAuthentication(CALLBACK_URL);
			}catch(Exception e){
				log.error("Unable to create google authentication", e);
			}
		}
		return googleAuthenticate;
	}
	
	private static IOAuthImpl getFacebookAuthentication(){
		if(facebookAuthenticate == null){
			try{
				facebookAuthenticate = new FacebookAuthentication(CALLBACK_URL);
			}catch(Exception e){
				log.error("Unable to create facebook authentication", e);
			}
		}
		return facebookAuthenticate;
	}
}
