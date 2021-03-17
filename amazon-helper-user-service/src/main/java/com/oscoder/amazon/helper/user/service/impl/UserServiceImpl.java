package com.oscoder.amazon.helper.user.service.impl;

import com.oscoder.amazon.helper.common.dto.ResponseDTO;
import com.oscoder.amazon.helper.common.utils.GsonUtils;
import com.oscoder.amazon.helper.user.api.dto.UserDTO;
import com.oscoder.amazon.helper.user.api.dto.UserInitDTO;
import com.oscoder.amazon.helper.user.api.dto.UserPwdDTO;
import com.oscoder.amazon.helper.user.api.enums.LoginType;
import com.oscoder.amazon.helper.user.api.service.UserService;
import com.oscoder.amazon.helper.user.service.data.mapper.UserMapper;
import com.oscoder.amazon.helper.user.service.data.po.UserPO;
import com.oscoder.amazon.helper.user.service.transform.UserPoDtoTransform;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author
 * @create 2021-02-20 10:56 PM
 **/
@Slf4j
@Component
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public ResponseDTO register(UserInitDTO userInitDTO) {
		try {
			//TODO 锁验证
			UserPO userPo = userMapper.getUserByLogin(LoginType.TEL.getType(), userInitDTO.getTel());
			if (userPo != null) {
				return ResponseDTO.fail("该手机号已经注册");
			}
			userMapper.insert(UserPoDtoTransform.INSTANCE.initDto2Po(userInitDTO));
			return ResponseDTO.success("注册成功");
		}catch (Exception e){
			log.error("register error for {}", GsonUtils.toJson(userInitDTO),e);
			return ResponseDTO.fail("注册失败，请重试");
		}
	}

	@Override
	public UserDTO getUser(Integer userId) {
		UserPO userPo = userMapper.getUserById(userId);
		return UserPoDtoTransform.INSTANCE.po2Dto(userPo);
	}

	@Override
	public UserDTO getUserByLogin(Integer loginType, String loginName) {
		UserPO userPo = userMapper.getUserByLogin(loginType, loginName);
		return UserPoDtoTransform.INSTANCE.po2Dto(userPo);
	}

	@Override
	public UserPwdDTO getUserPwdDTO(Integer loginType, String loginName) {
		UserPO userPwd = userMapper.getUserByLogin(loginType, loginName);
		return UserPoDtoTransform.INSTANCE.po2PwdDto(userPwd);
	}

	@Override
	public ResponseDTO<String> resetPwd(Integer userId, String pwd) {
		try {
			UserPO userPO = new UserPO();
			userPO.setId(userId);
			userPO.setPassword(pwd);
			userMapper.update(userPO);
			return ResponseDTO.success("修改成功");
		}catch (Exception e){
			log.error("resetPwd error for {}", GsonUtils.toJson(userId),e);
			return ResponseDTO.fail("修改失败，请重试");
		}
	}
}
