package com.self.service.servlet.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.base.Optional;
import com.self.care.store.jdbi.caches.FacebookUserCache;
import com.self.care.store.jdbi.caches.GoogleUserCache;
import com.self.care.store.jdbi.dao.UserDAO;
import com.self.care.store.jdbi.entity.UserBean;
import com.self.service.factory.authentication.AuthUserToUserBuilder;
import com.self.service.factory.authentication.EnumAuthenticationType;
import com.self.service.factory.authentication.OAuth2AuthenticationFactory;
import com.self.service.logging.impl.Log;
import com.self.service.logging.log.LogFactory;
import com.self.service.util.authentication.facebook.FacebookUserInfoEntity;
import com.self.service.util.authentication.google.GmailUserInfoEntity;
import com.self.service.util.impl.IOAuthImpl;

public class OAuthCallbackServlet extends HttpServlet {

	private static final Log log = LogFactory.getLogger("com.self.service.servlet.authenticate.OAuthCallbackServlet");
	
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
					
					AuthUserToUserBuilder.Builder authUser = null;
					
					switch(type){
					case GMAIL:
						GmailUserInfoEntity gmailUserInfoEntity = new GmailUserInfoEntity.Builder().setJSON(json.get()).build();
						authUser = new AuthUserToUserBuilder.Builder().setGoogleObject(gmailUserInfoEntity);
						break;
					case FACEBOOK:
						FacebookUserInfoEntity facebookUserInfoEntity = new FacebookUserInfoEntity.Builder().setJSON(json.get()).build();
						authUser = new AuthUserToUserBuilder.Builder().setFacebookObject(facebookUserInfoEntity);
						break;
					}
					
					createOrModifyUser(authUser, type);
					
					allOk = true;
				}
			}
		}
		
		if(allOk == false){
			resp.sendError(505);
		}else{
			PrintWriter out = resp.getWriter();
			out.write("Successfully logged in");
		}
		
	}

	private EnumAuthenticationType checkTokenKey(String tokenKey) {
		if (tokenKey.startsWith(IOAuthImpl.FACEBOOK_KEY))
			return EnumAuthenticationType.FACEBOOK;
		if (tokenKey.startsWith(IOAuthImpl.GOOGLE_KEY))	
			return EnumAuthenticationType.GMAIL;
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
	
	private long createOrModifyUser(AuthUserToUserBuilder.Builder authUserBuilder, EnumAuthenticationType enumAuthType){
		
		UserBean userBean = null;
		Timestamp accessTimeStamp = new Timestamp(new Date().getTime());
		
		switch(enumAuthType){
		case GMAIL:
			try {
				String userId = authUserBuilder.getGoogleUserId();
				userBean = GoogleUserCache.getInstance().getValue(userId);
				authUserBuilder.setUserBean(userBean);
			} catch (ExecutionException e) {
				log.error("Encountered data extraction error:", e);
			}
			userBean = authUserBuilder.build(accessTimeStamp, 1).getUserBean();
		case FACEBOOK:
			try {
				String userId = authUserBuilder.getGoogleUserId();
				userBean = FacebookUserCache.getInstance().getValue(userId);
				authUserBuilder.setUserBean(userBean);
			} catch (ExecutionException e) {
				log.error("Encountered data extraction error:", e);
			}
			userBean = authUserBuilder.build(accessTimeStamp, 2).getUserBean();
		}
		
		long userId = UserDAO.getInstance().updateOrInsertUser(userBean);
		
		return userId;
		
	}
}
