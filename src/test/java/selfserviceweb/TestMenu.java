package selfserviceweb;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.self.care.store.jdbi.caches.ImageCategoryCache;
import com.self.care.store.jdbi.caches.MenuListCache;
import com.self.service.function.ImageFunction;
import com.self.service.function.MenuFunction;

public class TestMenu {

	@Ignore
	@Test
	public void testMenu() {
		System.out.println(MenuFunction.getMainMenuList());
	}

	@Test
	public void testMenuCaching() {
		// Obtain the menu list directly first.
		String menuList = MenuFunction.getMainMenuList();
		String menuList2 = MenuFunction.getMainMenuList();

		// See that the address directory must be the same.
		Assert.assertTrue(menuList.hashCode() == menuList2.hashCode());
		// Let's refresh and make a new
		MenuListCache.getInstance().refreshCache();
		menuList2 = MenuFunction.getMainMenuList();
		
		// Both address directory will not be the same. Psst thinking of using intern.
		Assert.assertFalse(menuList == menuList2);
	}
	
	@Test
	public void testImageResult() {
		// Obtain the menu list directly first.
		String imageNameList = ImageFunction.getIntroImages().getImageNameAsJS();
		String imageSrcList = ImageFunction.getIntroImages().getImageSrcAsJS();
		System.out.println(imageSrcList);
		System.out.println(imageNameList);
		Assert.assertFalse(imageSrcList.endsWith(","));
		Assert.assertFalse(imageNameList.endsWith(","));
	}
}
