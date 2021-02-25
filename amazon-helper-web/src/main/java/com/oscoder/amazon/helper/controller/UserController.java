package com.oscoder.amazon.helper.controller;

import com.oscoder.amazon.helper.user.api.service.UserService;
import com.oscoder.amazon.helper.transform.UserDtoVoTransform;
import com.oscoder.amazon.helper.vo.ResponseVo;
import com.oscoder.amazon.helper.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @create 2021-02-20 11:19 PM
 **/

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@GetMapping("{id}")
	ResponseVo<UserVO> getUser(@PathVariable("id") Integer id){
		return ResponseVo.success(UserDtoVoTransform.INSTANCE.dto2DVo(userService.getUser(id)));
	}
	
}
