package com.self.service.function;

import com.self.service.function.util.ImageFunctionUtil;

public class ImageFunction{

	public static String getIntroImages(){
		return ImageFunctionUtil.getInstance().getIntroImage();
	}
}
