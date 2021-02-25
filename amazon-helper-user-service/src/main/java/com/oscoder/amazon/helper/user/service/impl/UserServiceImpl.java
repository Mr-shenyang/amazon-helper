package com.oscoder.amazon.helper.user.service.impl;

import com.oscoder.amazon.helper.user.api.dto.UserDTO;
import com.oscoder.amazon.helper.user.api.dto.UserPwdDTO;
import com.oscoder.amazon.helper.user.api.service.UserService;
import com.oscoder.amazon.helper.user.service.data.mapper.UserMapper;
import com.oscoder.amazon.helper.user.service.data.po.UserPO;
import com.oscoder.amazon.helper.user.service.transform.UserPoDtoTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author
 * @create 2021-02-20 10:56 PM
 **/
@Component
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Override
	public UserDTO getUser(Integer userId) {
		UserPO userPo = userMapper.getUserById(userId);
		return UserPoDtoTransform.INSTANCE.po2Dto(userPo);
	}
	
	@Override
	public UserPwdDTO getUserPwdDTO(Integer loginType, String loginName) {
		UserPO userPwd = userMapper.getUserPwd(loginType, loginName);
		return UserPoDtoTransform.INSTANCE.po2PwdDto(userPwd);
	}

	@Override
	public UserPwdDTO getUserPwdDTOById(Integer userId) {
		UserPO userPwd = userMapper.getFullUserById(userId);
		return UserPoDtoTransform.INSTANCE.po2PwdDto(userPwd);
	}
}
