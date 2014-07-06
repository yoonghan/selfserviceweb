package selfserviceweb;

import junit.framework.Assert;

import org.junit.Test;

import com.self.service.function.util.ImageFunctionUtil;
import com.self.service.function.util.MenuBtnFunctionUtil;
import com.self.service.function.util.MenuFunctionUtil;
import com.self.service.settings.WebSetting;

public class TestMenu {

	@Test
	public void testMenuCaching() {
		// Obtain the menu list directly first.
		String menuList = MenuFunctionUtil.getInstance().getMenuList(WebSetting.INTRO_MENU);

		System.out.println(menuList);
		
		Assert.assertEquals("[{\"menuId\":2,\"menu\":{\"menuId\":2,\"toolTip\":\"All about us\",\"textDisplay\":\"About Us\",\"linkURI\":\"./webby/webpage/about/\",\"enumTypeId\":4},\"level\":0,\"levelOrder\":0},{\"menuId\":3,\"menu\":{\"menuId\":3,\"toolTip\":\"Site Design\",\"textDisplay\":\"Architecture\",\"linkURI\":\"./webby/webpage/architecture/\",\"enumTypeId\":4},\"level\":0,\"levelOrder\":1},{\"menuId\":4,\"menu\":{\"menuId\":4,\"toolTip\":\"Contact Us\",\"textDisplay\":\"Contact Us\",\"linkURI\":\"./webby/webpage/about/#contactus\",\"enumTypeId\":4},\"level\":0,\"levelOrder\":2},{\"menuId\":11,\"menu\":{\"menuId\":11,\"toolTip\":\"Status on created site\",\"textDisplay\":\"Blog\",\"linkURI\":\"./webby/webpage/blog/\",\"enumTypeId\":4},\"level\":0,\"levelOrder\":3}]",
				menuList);
	}
	
	@Test
	public void testImageResult() {
		// Obtain the menu list directly first.
		String imageNameList = ImageFunctionUtil.getInstance().getIntroImage();
		
		System.out.println(imageNameList);
		
		Assert.assertFalse(imageNameList.endsWith(","));
	}
	
	@Test
	public void testMenuBtn() {
		String menuBtn = MenuBtnFunctionUtil.getInstance().getMenuBtn("1");
		
		System.out.println(menuBtn);
		
		Assert.assertFalse("".equals(menuBtn));
	}
}
