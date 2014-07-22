package com.self.service.factory.authentication;

import java.sql.Timestamp;

import com.self.care.store.jdbi.entity.UserBean;
import com.self.service.util.authentication.facebook.FacebookUserInfoEntity;
import com.self.service.util.authentication.google.GoogleUserInfoEntity;

public class AuthUserToUserBuilder {
	
	final UserBean userBean;
	
	public AuthUserToUserBuilder(UserBean userBean){
		this.userBean = userBean;
	}
	
	public UserBean getUserBean(){
		return userBean;
	}
	
	public static class Builder{
		
		GoogleUserInfoEntity gmailUserInfo = null;
		FacebookUserInfoEntity facebookUserInfo = null;
		UserBean userBean = null;
		
		public Builder setUserBean(UserBean userBean){
			this.userBean = userBean;
			return this;
		}
		
		public Builder setGoogleJSON(String json){
			gmailUserInfo = new GoogleUserInfoEntity.Builder().setJSON(json).build();
			return this;
		}
		
		public Builder setFacebookJSON(String json){
			facebookUserInfo = new FacebookUserInfoEntity.Builder().setJSON(json).build();
			return this;
		}
		
		public Builder setGoogleObject(GoogleUserInfoEntity gmailUserInfo){
			this.gmailUserInfo = gmailUserInfo;
			return this;
		}
		
		public Builder setFacebookObject(FacebookUserInfoEntity facebookUserInfo){
			this.facebookUserInfo = facebookUserInfo;
			return this;
		}
		
		public String getGoogleUserId(){
			if(gmailUserInfo  == null)
				return "";
			
			return gmailUserInfo.getId();
		}
		
		public String getFacebookUserId(){
			if(facebookUserInfo  == null)
				return "";
			
			return facebookUserInfo.getId();
		}
		
		public AuthUserToUserBuilder build(Timestamp lastLoginTimeStamp, int loginType){
			
			if(userBean == null){
				userBean = new UserBean();
				userBean.setLastModifiedTimeStamp(lastLoginTimeStamp);
			}
			
			if(userBean.getTypeMap() == null)
				userBean.setTypeMap(loginType);
						
			if(gmailUserInfo != null){
				userBean.setGoogleEmail(gmailUserInfo.getEmail());
				userBean.setGoogleAuthId(gmailUserInfo.getId());
				userBean.setGoogleLink(gmailUserInfo.getLink());
				userBean.setGooglePicture(gmailUserInfo.getPicture());
				if(userBean.getName() == null){
					userBean.setName(gmailUserInfo.getName());
					userBean.setIdentity(gmailUserInfo.getName());
				}

				if(userBean.getEmail() == null && gmailUserInfo.getEmail() != null)
					userBean.setEmail(gmailUserInfo.getEmail());
			}
			
			if(facebookUserInfo != null){
				userBean.setFacebookEmail(facebookUserInfo.getEmail());
				userBean.setFacebookAuthId(facebookUserInfo.getId());
				userBean.setFacebookLink(facebookUserInfo.getLink());
				userBean.setFacebookGender(facebookUserInfo.getGender());
				
				if(userBean.getName() == null){
					userBean.setName(facebookUserInfo.getName());
					userBean.setIdentity(facebookUserInfo.getName());	
				}

				if(userBean.getEmail() == null && facebookUserInfo.getEmail() != null)
					userBean.setEmail(facebookUserInfo.getEmail());
			}
			
			userBean.setLastLoginTypeMap(loginType);
			userBean.setLastLoginTimeStamp(lastLoginTimeStamp);
			
			return new AuthUserToUserBuilder(userBean);
		}
	}
}
