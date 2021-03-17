package com.oscoder.amazon.helper.user.api.service;

import com.oscoder.amazon.helper.common.dto.ResponseDTO;
import com.oscoder.amazon.helper.user.api.dto.UserDTO;
import com.oscoder.amazon.helper.user.api.dto.UserInitDTO;
import com.oscoder.amazon.helper.user.api.dto.UserPwdDTO;

/**
 * @author
 * @create 2021-02-20 10:48 AM
 **/
public interface UserService {
	/**
	 * 注册
	 * @param userInitDTO
	 * @return
	 */
	ResponseDTO<String> register(UserInitDTO userInitDTO);
	/**
	 * 获取用户信息
	 * @param userId
	 * @return
	 */
	UserDTO getUser(Integer userId);

	/**
	 * 获取登录信息
	 * @param loginType @link com.oscoder.amazon.helper.user.api.enums.LoginType
	 * @param loginName
	 * @return
	 */
	UserDTO getUserByLogin(Integer loginType,String loginName);

	/**
	 * 获取登录信息
	 * @param loginType @link com.oscoder.amazon.helper.user.api.enums.LoginType
	 * @param loginName
	 * @return
	 */
	UserPwdDTO getUserPwdDTO(Integer loginType,String loginName);

	/**
	 * 重置密码
	 * @param userId
	 * @param pwd
	 * @return
	 */
	ResponseDTO<String> resetPwd(Integer userId,String pwd);
}
