package com.oscoder.amazon.helper.controller;

import com.oscoder.amazon.helper.common.dto.ResponseDTO;
import com.oscoder.amazon.helper.user.api.dto.UserDTO;
import com.oscoder.amazon.helper.user.api.dto.UserInitDTO;
import com.oscoder.amazon.helper.user.api.dto.UserPwdDTO;
import com.oscoder.amazon.helper.user.api.enums.LoginType;
import com.oscoder.amazon.helper.user.api.enums.UserType;
import com.oscoder.amazon.helper.user.api.service.UserService;
import com.oscoder.amazon.helper.vo.ResponseVo;
import com.oscoder.amazon.helper.vo.UserPwdVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/anno")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseVo<String> login(@RequestBody UserPwdVo userPwdVo){
        UserPwdDTO user = userService.getUserPwdDTO(LoginType.TEL.getType(), userPwdVo.getPhone());
        if (user == null) {
            return ResponseVo.fail("该手机号尚未注册");
        }
        if (user.getPassword().equals(userPwdVo.getPwd())) {
            return ResponseVo.success("");
        }
        return ResponseVo.fail("密码错误");
    }

    @PostMapping("/register")
    public ResponseVo<String> register(@RequestBody UserPwdVo userPwdVo){
        UserInitDTO userInitDTO = new UserInitDTO();
        userInitDTO.setName(userPwdVo.getPhone());
        userInitDTO.setType(UserType.COMMON.getType());
        userInitDTO.setTel(userPwdVo.getPhone());
        userInitDTO.setPassword(userPwdVo.getPwd());
        ResponseDTO<String> registerResp = userService.register(userInitDTO);
        return new ResponseVo<String>(registerResp.getCode(),registerResp.getData(),registerResp.getFailMsg());
    }
    @PostMapping("/resetpwd")
    public ResponseVo<String> resetPwd(@RequestBody UserPwdVo userPwdVo){
        UserDTO user = userService.getUserByLogin(LoginType.TEL.getType(), userPwdVo.getPhone());
        if (user == null) {
            return ResponseVo.fail("该手机号尚未注册");
        }
        ResponseDTO<String> registerResp = userService.resetPwd(user.getId(),userPwdVo.getPwd());
        return new ResponseVo<String>(registerResp.getCode(),registerResp.getData(),registerResp.getFailMsg());
    }

    @GetMapping("/sendverificationcode")
    public ResponseVo<String> sendVerificationCode(@RequestParam("phone") String phone,
                                                   @RequestParam(name = "register",defaultValue="true") Boolean register){
        UserDTO user = userService.getUserByLogin(LoginType.TEL.getType(), phone);
        if(register){
            if (user != null) {
                return ResponseVo.fail("该手机号已经注册");
            }
        }else {
            if (user == null) {
                return ResponseVo.fail("该手机号尚未注册");
            }
        }
        return ResponseVo.success("发送成功");
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);//设置存活时间，“0”即马上消失
        cookie.setPath("/#login");
        response.addCookie(cookie);
        return "redirect:/#login";
    }
}
