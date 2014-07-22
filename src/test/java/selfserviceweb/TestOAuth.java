package selfserviceweb;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;

import com.self.care.store.jdbi.dao.UserDAO;
import com.self.care.store.jdbi.entity.UserBean;
import com.self.service.factory.authentication.EnumAuthenticationType;
import com.self.service.servlet.authentication.OAuthCallbackServlet;

public class TestOAuth {
	
	public final String userId = "011";
	
	@Test
	public void testOrder(){
		createFacebookUser();
		createGoogleUser();
		createFacebookAndGoogleUser();
	}
	
	public void createFacebookUser(){
		String json = "{'id':'"+userId+"','email':'test@email','name':'test name','link':'http://testlink.org','gender':'male'}"; 
		EnumAuthenticationType facebookType = EnumAuthenticationType.FACEBOOK;
		
		OAuthCallbackServlet callbackServlet = new OAuthCallbackServlet();
		callbackServlet.parseJSONToCreateUser(json, facebookType);
		
		UserBean userBean = null;
		userBean = UserDAO.getInstance().getUserViaFacebook(userId);
		
		Assert.assertTrue(userBean.getIdentity()!=null); //only 1 record and 1 only exist.
	}
	
	public void createGoogleUser(){
		String json = "{'id':"+userId+",'email':'test@email','verified_email':true,"
				+ "'name':'test name','link':'http://testlink.org',"
				+ "'family_name':'family','given_name':'given','picture':'testpicture'}"; 
		EnumAuthenticationType googleType = EnumAuthenticationType.GOOGLE;
		
		OAuthCallbackServlet callbackServlet = new OAuthCallbackServlet();
		callbackServlet.parseJSONToCreateUser(json, googleType);
		
		UserBean userBean = null;
		userBean = UserDAO.getInstance().getUserViaGoogle(userId);

		Assert.assertTrue(userBean.getIdentity()!=null); //only 1 record and 1 only exist.
		
	}
	
	public void createFacebookAndGoogleUser(){
		
		int rowCount = UserDAO.getInstance().getCount();
		
		if(rowCount==0)
			Assert.assertFalse(true);
		
		createFacebookUser();
		createGoogleUser();
		
		//recreate again and make sure only 1 record exist.
		Assert.assertEquals(rowCount, UserDAO.getInstance().getCount());
	}
	
	@After
	public void cleanup(){
		System.out.println("Clean up");
		
		UserBean userBean;
		userBean = UserDAO.getInstance().getUserViaGoogle(userId);
		System.out.println("Deleting"+userBean.getUserId());
		UserDAO.getInstance().actualUserDelete(userBean.getUserId());
		
		userBean = UserDAO.getInstance().getUserViaFacebook(userId);
		System.out.println("Deleting"+userBean.getUserId());
		UserDAO.getInstance().actualUserDelete(userBean.getUserId());
	}
}
