package com.oscoder.amazon.helper.user.api.enums;

/**
 * @author
 * @create 2020-04-30 17:33
 **/
public enum LoginType {
	TEL(1),
	EMAIL(2 ),
	;
	
	LoginType(Integer type) {
		this.type = type;
	}
	
	Integer type;
	
	public Integer getType() {
		return type;
	}}
