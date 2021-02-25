package com.oscoder.amazon.helper.user.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author
 * @create 2021-02-21 3:44 PM
 **/
@ToString
@Getter
@Setter
public class UserPwdDTO {
	private Integer id;
	private String name;
	private String password;
}
