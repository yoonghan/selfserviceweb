package com.self.service.function;

import com.self.service.function.entity.ImageListObject;
import com.self.service.function.util.ImageFunctionUtil;

public class ImageFunction{

	public static ImageListObject getIntroImages(){
		return ImageFunctionUtil.getInstance().getIntroImage();
	}
}
