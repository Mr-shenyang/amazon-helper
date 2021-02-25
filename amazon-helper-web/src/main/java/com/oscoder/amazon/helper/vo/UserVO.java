package com.oscoder.amazon.helper.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author
 * @create 2021-02-20 11:23 PM
 **/
@ToString
@Getter
@Setter
public class UserVO {
	private Integer id;
	private Integer type;
	private String name;
	private String code;
	private String email;
	private String tel;
}
