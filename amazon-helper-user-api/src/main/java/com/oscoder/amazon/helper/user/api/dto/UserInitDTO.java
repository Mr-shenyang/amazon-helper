package com.oscoder.amazon.helper.user.api.dto;

import com.oscoder.amazon.helper.user.api.enums.UserType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author
 * @create 2020-04-22 11:44
 **/
@Getter
@Setter
@ToString
public class UserInitDTO {
	/**
	 * 类型
	 * @see UserType
	 */
	private Integer type;
	private String name;
	private String code;
	private String email;
	private String tel;
	private String password;
}
