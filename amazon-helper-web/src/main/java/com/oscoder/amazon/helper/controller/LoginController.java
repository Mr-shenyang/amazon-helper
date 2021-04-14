package com.oscoder.amazon.helper.controller;

import com.oscoder.amazon.helper.common.dto.ResponseDTO;
import com.oscoder.amazon.helper.jwt.JWTUtil;
import com.oscoder.amazon.helper.user.api.dto.UserDTO;
import com.oscoder.amazon.helper.user.api.dto.UserInitDTO;
import com.oscoder.amazon.helper.user.api.dto.UserPwdDTO;
import com.oscoder.amazon.helper.user.api.enums.LoginType;
import com.oscoder.amazon.helper.user.api.enums.UserType;
import com.oscoder.amazon.helper.user.api.service.UserService;
import com.oscoder.amazon.helper.vo.ResponseVo;
import com.oscoder.amazon.helper.vo.UserPwdVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
    public ResponseVo<String> login(@RequestBody UserPwdVo userPwdVo,HttpServletResponse response){


        try {
            UserPwdDTO userByLogin = userService.getUserPwdDTO(LoginType.TEL.getType(), userPwdVo.getPhone());
            if (userByLogin == null) {
                return ResponseVo.fail("用户不存在，请注册");
            }
            if (!userByLogin.getPassword().equals(userPwdVo.getPwd())) {
                return ResponseVo.fail("密码错误，请重新输入");
            }

//            //将用户请求参数封装后，直接提交给Shiro处理
//            Subject subject = SecurityUtils.getSubject();
//            UsernamePasswordToken token = new UsernamePasswordToken(userPwdVo.getPhone(), userPwdVo.getPwd());
//            subject.login(token);
//            //Shiro认证通过后会将user信息放到subject内，生成token并返回
//            UserPwdDTO user = (UserPwdDTO) subject.getPrincipal();
            String newToken = JWTUtil.sign(userByLogin.getName(),userByLogin.getId());
            response.setHeader("token", newToken);
            return ResponseVo.success("");
//        } catch (AuthenticationException e) {
//            // 如果校验失败，shiro会抛出异常，返回客户端失败
//            log.error("User {} login fail, Reason:{}", userPwdVo.getPhone(), e.getMessage());
//            return ResponseVo.fail(e.getMessage());
        } catch (Exception e) {
            return ResponseVo.fail(e.getMessage());
        }
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
        SecurityUtils.getSubject().logout();
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);//设置存活时间，“0”即马上消失
        cookie.setPath("/#login");
        response.addCookie(cookie);
        return "redirect:/#login";
    }
}
