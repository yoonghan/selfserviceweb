package com.jaring.jom.servlet.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.base.Optional;
import com.jaring.jom.factory.authentication.EnumAuthenticationType;
import com.jaring.jom.factory.authentication.OAuth2AuthenticationFactory;
import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;
import com.jaring.jom.util.impl.IOAuthImpl;

public class OAuthSenderServlet extends HttpServlet{

	private static final Log log = LogFactory.getLogger(OAuthSenderServlet.class.getName());
	
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
		HttpSession session = req.getSession(true);
		
		String reqType = req.getParameter("type");
		EnumAuthenticationType type = EnumAuthenticationType.getById(reqType);
		
		Optional<String> url = Optional.absent();
		
		if(type != null){
			IOAuthImpl ioImpl = OAuth2AuthenticationFactory.getOAuth(type);
			
			String stateToken = ioImpl.generateStateToken();
			url =ioImpl.getGeneratedOAuthURL(stateToken);
			
			session.setAttribute("state", stateToken);
			
			log.info("Redirecting for:-"+url.get());
		}
		
		if(url.isPresent()){
			resp.sendRedirect(url.get());
		}else{
			resp.sendError(505);
		}
	}
	
}
