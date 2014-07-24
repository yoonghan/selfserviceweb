package com.jaring.jom.factory.authentication;

public enum EnumAuthenticationType {
	GOOGLE("G"),
	FACEBOOK("F");
	
	private String id = null;
	
	EnumAuthenticationType(String id){
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public static EnumAuthenticationType getById(final String id) {
		EnumAuthenticationType propDef = null;
		if (id != null) {
			for (EnumAuthenticationType e : EnumAuthenticationType.values()) {
				if (e.getId().equals(id)) {
					propDef = e;
					break;
				}
			}
		}
		return propDef;
	}
}
