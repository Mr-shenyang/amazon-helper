package com.oscoder.amazon.helper.shiro;

import com.oscoder.amazon.helper.jwt.JWTToken;
import com.oscoder.amazon.helper.user.api.dto.UserPwdDTO;
import com.oscoder.amazon.helper.user.api.service.UserService;
import com.oscoder.amazon.helper.jwt.JWTUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author
 * @create 2021-02-21 2:04 PM
 **/
public class UserRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		String token = (String) authenticationToken.getCredentials();
		// 解密获得userId，用于和数据库进行对比
		Integer userId = JWTUtil.getUserId(token);
		if (userId == null) {
			throw new AuthenticationException("token invalid");
		}

		UserPwdDTO user = userService.getUserPwdDTOById(userId);
		if (user == null) {
			throw new AuthenticationException("User didn't existed!");
		}

		if (!JWTUtil.verify(token, user.getName(),user.getId(), user.getPassword())) {
			throw new AuthenticationException("Username or password error");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
		return info;
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JWTToken;
	}
}
