package com.oscoder.amazon.helper.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ToString
@Getter
@Setter
public class UserPwdVo {
    @NotBlank(message = "手机号不允许为空！")
    private String phone;
    String pwd;
}
