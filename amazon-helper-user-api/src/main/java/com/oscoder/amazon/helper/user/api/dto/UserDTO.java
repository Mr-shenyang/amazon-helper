package com.oscoder.amazon.helper.user.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户信息
 * @author
 * @create 2020-04-22 11:42
 **/
@Getter
@Setter
@ToString
public class UserDTO {
	private Integer id;
	private Integer type;
	private String name;
	private String email;
	private String tel;
	private String salt = "sfdsfadfdksd";
}
