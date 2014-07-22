package com.self.service.factory.authentication;

import com.self.service.logging.impl.Log;
import com.self.service.logging.log.LogFactory;
import com.self.service.util.authentication.facebook.FacebookAuthentication;
import com.self.service.util.authentication.google.GoogleAuthentication;
import com.self.service.util.impl.IOAuthImpl;
import static com.self.service.settings.WebSetting.*;

public class OAuth2AuthenticationFactory {
	
	private static Log log = LogFactory.getLogger("com.self.service.function.util.OAuth2AuthenticationFactory");
		
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
