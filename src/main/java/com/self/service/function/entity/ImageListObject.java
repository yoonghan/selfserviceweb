package com.self.service.function.entity;

public class ImageListObject {

	private String imageSrcAsJS;
	private String imageNameAsJS;
	
	public ImageListObject(String imageSrcAsJS, String imageNameAsJS){
		this.imageSrcAsJS = imageSrcAsJS;
		this.imageNameAsJS = imageNameAsJS;
	}
	
	public String getImageSrcAsJS() {
		return imageSrcAsJS;
	}
	
	public void setImageSrcAsJS(String imageSrcAsJS) {
		this.imageSrcAsJS = imageSrcAsJS;
	}
	
	public String getImageNameAsJS() {
		return imageNameAsJS;
	}
	
	public void setImageNameAsJS(String imageNameAsJS) {
		this.imageNameAsJS = imageNameAsJS;
	}
}
