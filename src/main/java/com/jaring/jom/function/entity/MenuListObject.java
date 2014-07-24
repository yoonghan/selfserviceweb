package com.jaring.jom.function.entity;

public class MenuListObject{
	private final String stmt;
	private final int index;
	
	public MenuListObject(String stmt, int index){
		this.stmt=stmt;
		this.index=index;
	}
	
	public String getStmt() {
		return stmt;
	}

	public int getIndex() {
		return index;
	}

}