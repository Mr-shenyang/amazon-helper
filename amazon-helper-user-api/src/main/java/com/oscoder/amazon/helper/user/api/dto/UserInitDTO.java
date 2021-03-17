package com.oscoder.amazon.helper.user.api.dto;

import com.oscoder.amazon.helper.user.api.enums.UserType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

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
	@NotBlank(message = "用户类型不能为空")
	private Integer type;
	private String name;
	private String email;
	@NotBlank(message = "手机号不可为空")
	private String tel;
	@NotBlank(message = "密码不能为空")
	private String password;
}
