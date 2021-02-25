package com.oscoder.amazon.helper.user.api.enums;

/**
 * @author
 * @create 2020-04-22 11:39
 **/
public enum  UserType {
	ADMIN(1),
	COMMON(2);
	
	private Integer type;
	
	UserType(int type) {
		this.type = type;
	}
	
	public static UserType of(Integer type){
		if (type == null) {
			return null;
		}
		for (UserType userType : UserType.values()) {
			if (userType.getType().equals(type)) {
				return userType;
			}
		}
		return null;
	}
	
	public Integer getType() {
		return type;
	}}
