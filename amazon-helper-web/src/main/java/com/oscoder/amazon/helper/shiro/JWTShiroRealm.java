package com.oscoder.amazon.helper.shiro;

import com.oscoder.amazon.helper.jwt.JWTToken;
import com.oscoder.amazon.helper.jwt.JWTUtil;
import com.oscoder.amazon.helper.user.api.dto.UserDTO;
import com.oscoder.amazon.helper.user.api.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class JWTShiroRealm extends AuthorizingRealm {
    private UserService userService;

    public JWTShiroRealm(UserService userService) {
        this.userService = userService;
        this.setCredentialsMatcher(new JWTCredentialsMatcher());
    }

    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        if (token == null) {
            throw new AuthenticationException("token认证失败！");
        }
        // 解密获得username，用于和数据库进行对比
        Integer userId = JWTUtil.getUserId(token);
        String userName = JWTUtil.getUserName(token);
        if (userId == null || !JWTUtil.verify(token, userName, userId)) {
            throw new AuthenticationException("token认证失败！");
        }
        UserDTO user = userService.getUser(userId);
        if (user == null) {
            throw new AuthenticationException("该用户不存在！");
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }

    @Override
    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }
}