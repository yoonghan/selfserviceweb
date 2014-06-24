package com.self.service.function.entity;

public class HtmlTagCacheObject <V>{
	private final int hashCode;
	private final V htmlTag;
	
	public HtmlTagCacheObject(int hashCode, V htmlTag){
		this.hashCode = hashCode;
		this.htmlTag = htmlTag;
	}

	public int getHashCode() {
		return hashCode;
	}

	public V getHtmlTag() {
		return htmlTag;
	}
	
	
}
