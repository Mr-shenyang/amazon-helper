package com.oscoder.amazon.helper.user.service.data.po;

import lombok.Getter;
import lombok.Setter;

/**
 * @author
 * @create 2020-04-22 11:25
 **/
@Getter
@Setter
public class UserPO {
	private Integer id;
	/**
	 * 类型
	 * @see com.oscoder.amazon.helper.user.api.enums.UserType
	 */
	private Integer type;
	
	private String name;
	private String email;
	private String tel;
	private String password;
	/**
	 * 状态 0 无需 1 有效
	 */
	private Integer status;
}
