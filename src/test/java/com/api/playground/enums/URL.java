package com.api.playground.enums;

public enum URL {
	
	PRODUCT("/products"), 
	SERVICE("/services"), 
	HEALTH("/healthcheck");
	
	private String value;

	URL(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
