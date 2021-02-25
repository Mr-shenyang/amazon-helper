package com.oscoder.amazon.helper.controller;

import com.oscoder.amazon.helper.exception.UnauthorizedException;
import com.oscoder.amazon.helper.user.api.dto.UserPwdDTO;
import com.oscoder.amazon.helper.user.api.service.UserService;
import com.oscoder.amazon.helper.jwt.JWTUtil;
import com.oscoder.amazon.helper.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseVo<String> login(@RequestParam("loginType") Integer loginType,
                                    @RequestParam("username") String username,
                                    @RequestParam("password") String password) {
        UserPwdDTO userPwdDTO = userService.getUserPwdDTO(loginType, username);
        if (userPwdDTO.getPassword().equals(password)) {
            return ResponseVo.success(JWTUtil.sign(userPwdDTO.getName(), userPwdDTO.getId(), password));
        } else {
            throw new UnauthorizedException();
        }
    }
}
