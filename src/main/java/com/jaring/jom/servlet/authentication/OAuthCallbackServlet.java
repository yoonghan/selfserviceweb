package com.jaring.jom.servlet.authentication;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.base.Optional;
import com.jaring.jom.factory.authentication.AuthUserToUserBuilder;
import com.jaring.jom.factory.authentication.EnumAuthenticationType;
import com.jaring.jom.factory.authentication.OAuth2AuthenticationFactory;
import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;
import com.jaring.jom.settings.WebSetting;
import com.jaring.jom.store.jdbi.dao.DBDAO;
import com.jaring.jom.store.jdbi.entity.UserBean;
import com.jaring.jom.util.authentication.facebook.FacebookUserInfoEntity;
import com.jaring.jom.util.authentication.google.GoogleUserInfoEntity;
import com.jaring.jom.util.impl.IOAuthImpl;

public class OAuthCallbackServlet extends HttpServlet {

	private static final Log log = LogFactory.getLogger(OAuthCallbackServlet.class.getName());
	
	private final String TOKEN_KEY = "state";

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doGet(req, resp);
		
		final HttpSession session = req.getSession(false);
		final String authCode = req.getParameter("code");
		final String tokenKey = req.getParameter(TOKEN_KEY);
		
		boolean allOk = false;
		
		if(isTokenValid(session, tokenKey, authCode)){
		
			EnumAuthenticationType type = checkTokenKey(tokenKey);
			
			if(type != null){
				Optional<String> json = OAuth2AuthenticationFactory.getOAuth(type).getUserInfoJson(authCode);
				
				if(json.isPresent()){
					log.info("Received user information for token:"+authCode);
					
					parseJSONToCreateUser(json.get(), type);
					
					allOk = true;
				}
			}
		}
		
		if(allOk == false){
			resp.sendError(505);
		}else{
			log.info("Redirecting to:"+WebSetting.CALLBACK_REDIRECT_URL);
			resp.sendRedirect(WebSetting.CALLBACK_REDIRECT_URL);
		}
		
	}

	public void parseJSONToCreateUser(String json, EnumAuthenticationType type) {
		AuthUserToUserBuilder.Builder authUser = null;
		Optional<UserBean> userBean = null;
		Timestamp accessTimeStamp = new Timestamp(new Date().getTime());
		int typeId = 0;
		boolean doUpdateOrInsert = true;
		
		switch(type){
		case GOOGLE:
			typeId = 1;
			GoogleUserInfoEntity gmailUserInfoEntity = new GoogleUserInfoEntity.Builder().setJSON(json).build();
			authUser = new AuthUserToUserBuilder.Builder().setGoogleObject(gmailUserInfoEntity);
			userBean = DBDAO.INSTANCE.getUser().getUserViaGoogle(authUser.getGoogleUserId());
			doUpdateOrInsert = compareSameGoogleAcct(gmailUserInfoEntity, userBean);
			break;
		case FACEBOOK:
			typeId = 2;
			FacebookUserInfoEntity facebookUserInfoEntity = new FacebookUserInfoEntity.Builder().setJSON(json).build();
			authUser = new AuthUserToUserBuilder.Builder().setFacebookObject(facebookUserInfoEntity);
			userBean = DBDAO.INSTANCE.getUser().getUserViaFacebook(authUser.getFacebookUserId());
			doUpdateOrInsert = compareSameFacebookAcct(facebookUserInfoEntity, userBean);
			break;
		}
		
		if(doUpdateOrInsert){
			if(userBean.isPresent())
				authUser.setUserBean(userBean.get());
			
			UserBean updatedUserBean = authUser.build(accessTimeStamp, typeId).getUserBean();
			
			long userId = DBDAO.INSTANCE.getUser().updateOrInsertUser(updatedUserBean);
			log.info("Created or updated user:"+userId);
		}
	}

	private boolean compareSameFacebookAcct(
			FacebookUserInfoEntity entity, Optional<UserBean> userBean) {
		if(userBean.isPresent() == false || entity == null)
			return true;
		
		boolean compare = (entity.getEmail().equals(userBean.get().getFacebookEmail()) &&
				entity.getGender().equals(userBean.get().getFacebookGender()) &&
				entity.getLink().equals(userBean.get().getFacebookLink()))
				;
		
		return  false == compare;
	}

	private boolean compareSameGoogleAcct(GoogleUserInfoEntity entity,
			Optional<UserBean> userBean) {
		if(userBean.isPresent() == false || entity == null)
			return true;
		
		boolean compare = (entity.getEmail().equals(userBean.get().getGoogleEmail()) &&
				entity.getLink().equals(userBean.get().getGoogleLink()) &&
				entity.getPicture().equals(userBean.get().getGooglePicture())); 
		
		return  false == compare;
	}

	private EnumAuthenticationType checkTokenKey(String tokenKey) {
		if (tokenKey.startsWith(IOAuthImpl.FACEBOOK_KEY))
			return EnumAuthenticationType.FACEBOOK;
		if (tokenKey.startsWith(IOAuthImpl.GOOGLE_KEY))	
			return EnumAuthenticationType.GOOGLE;
		return null;
	}

	/**
	 * Important to check if session is created. Reminder to self that if session is not created, this login
	 * does not belong to anyone and must not allowed to be logged in.
	 */
	private boolean isTokenValid(HttpSession session, String tokenParameter, String authCode) {
		return tokenParameter != null && session !=null && authCode != null 
				&& !tokenParameter.isEmpty() && !authCode.isEmpty()
				&& tokenParameter.equals(session.getAttribute(TOKEN_KEY));
	}
}
