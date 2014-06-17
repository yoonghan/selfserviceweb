package com.self.service.function.entity;

public class HtmlTagCacheObject {
	private final int hashCode;
	private final String htmlTag;
	
	public HtmlTagCacheObject(int hashCode, String htmlTag){
		this.hashCode = hashCode;
		this.htmlTag = htmlTag;
	}

	public int getHashCode() {
		return hashCode;
	}

	public String getHtmlTag() {
		return htmlTag;
	}
	
	
}
