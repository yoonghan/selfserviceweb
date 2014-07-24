package selfserviceweb;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;

import com.google.common.base.Optional;
import com.jaring.jom.factory.authentication.EnumAuthenticationType;
import com.jaring.jom.servlet.authentication.OAuthCallbackServlet;
import com.jaring.jom.store.jdbi.dao.DBDAO;
import com.jaring.jom.store.jdbi.entity.UserBean;

public class TestOAuth {
	
	public final String userId = "011";
	public final String _modifyLink = "test2";
	public final String FACEBOOK_JSON = "{'id':'"+userId+"','email':'test@email','name':'test name','link':'http://testlink.org','gender':'male'}";
	public final String GOOGLE_JSON = "{'id':"+userId+",'email':'test@email','verified_email':true,'name':'test name','link':'http://testlink.org','family_name':'family','given_name':'given','picture':'testpicture'}";
	public final String MODIFY_FACEBOOK_JSON = "{'id':'"+userId+"','email':'test@email','name':'test name','link':'"+_modifyLink+"','gender':'male'}";
	public final String MODIFY_GOOGLE_JSON = "{'id':"+userId+",'email':'test@email','verified_email':true,'name':'test name','link':'"+_modifyLink+"','family_name':'family','given_name':'given','picture':'testpicture'}";
	
	@Test
	public void testOrder(){
		createFacebookUser();
		createGoogleUser();
		createFacebookAndGoogleUser();
		createModifiedFacebookUser();
		createModifiedGoogleUser();
	}
	
	private void createModifiedGoogleUser() {
		String json = MODIFY_GOOGLE_JSON;
		
		EnumAuthenticationType googleType = EnumAuthenticationType.GOOGLE;
		
		OAuthCallbackServlet callbackServlet = new OAuthCallbackServlet();
		callbackServlet.parseJSONToCreateUser(json, googleType);
		
		Optional<UserBean> userBean = null;
		userBean = DBDAO.INSTANCE.getUser().getUserViaGoogle(userId);

		Assert.assertTrue(userBean.get().getGoogleLink().equals(_modifyLink)); //only 1 record and 1 only exist.
	}

	private void createModifiedFacebookUser() {
		String json = MODIFY_FACEBOOK_JSON; 
		EnumAuthenticationType facebookType = EnumAuthenticationType.FACEBOOK;
		
		OAuthCallbackServlet callbackServlet = new OAuthCallbackServlet();
		callbackServlet.parseJSONToCreateUser(json, facebookType);
		
		Optional<UserBean> userBean = null;
		userBean = DBDAO.INSTANCE.getUser().getUserViaFacebook(userId);
		
		Assert.assertTrue(userBean.get().getFacebookLink().equals(_modifyLink)); //only 1 record and 1 only exist.
	}

	public void createFacebookUser(){
		String json = FACEBOOK_JSON; 
		EnumAuthenticationType facebookType = EnumAuthenticationType.FACEBOOK;
		
		OAuthCallbackServlet callbackServlet = new OAuthCallbackServlet();
		callbackServlet.parseJSONToCreateUser(json, facebookType);
		
		Optional<UserBean> userBean = null;
		userBean = DBDAO.INSTANCE.getUser().getUserViaFacebook(userId);
		
		Assert.assertTrue(userBean.get().getIdentity()!=null); //only 1 record and 1 only exist.
	}
	
	public void createGoogleUser(){
		String json = GOOGLE_JSON; 
		EnumAuthenticationType googleType = EnumAuthenticationType.GOOGLE;
		
		OAuthCallbackServlet callbackServlet = new OAuthCallbackServlet();
		callbackServlet.parseJSONToCreateUser(json, googleType);
		
		Optional<UserBean> userBean = null;
		userBean = DBDAO.INSTANCE.getUser().getUserViaGoogle(userId);

		Assert.assertTrue(userBean.get().getIdentity()!=null); //only 1 record and 1 only exist.
		
	}
	
	public void createFacebookAndGoogleUser(){
		
		int rowCount = DBDAO.INSTANCE.getUser().getCount();
		
		if(rowCount==0)
			Assert.assertFalse(true);
		
		createFacebookUser();
		createGoogleUser();
		
		//recreate again and make sure only 1 record exist.
		Assert.assertEquals(rowCount, DBDAO.INSTANCE.getUser().getCount());
	}
	
	@After
	public void cleanup(){
		System.out.println("Clean up");
		
		Optional<UserBean> userBean;
		userBean = DBDAO.INSTANCE.getUser().getUserViaGoogle(userId);
		if(userBean.isPresent()){
			System.out.println("Deleting"+userBean.get().getUserId());
			DBDAO.INSTANCE.getUser().actualUserDelete(userBean.get().getUserId());
		}
		
		userBean = DBDAO.INSTANCE.getUser().getUserViaFacebook(userId);
		if(userBean.isPresent()){
			System.out.println("Deleting"+userBean.get().getUserId());
			DBDAO.INSTANCE.getUser().actualUserDelete(userBean.get().getUserId());
		}
	}
}
