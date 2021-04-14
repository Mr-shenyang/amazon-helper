package com.oscoder.amazon.helper.shiro;

import com.oscoder.amazon.helper.user.api.dto.UserPwdDTO;
import com.oscoder.amazon.helper.user.api.enums.LoginType;
import com.oscoder.amazon.helper.user.api.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.assertj.core.util.Lists;

public class DbShiroRealm extends AuthorizingRealm {
    private static final String encryptSalt = "F12839WhsnnEV$#23b";
    private UserService userService;

    public DbShiroRealm(UserService userService) {
        this.userService = userService;
    }
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken pwdToken = (UsernamePasswordToken)authenticationToken;
        String username = pwdToken.getUsername();
        UserPwdDTO user = userService.getUserPwdDTO(LoginType.TEL.getType(), username);
        if(user == null){
            throw new AuthenticationException("用户名或者密码错误");
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(encryptSalt), getName());

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        UserPwdDTO user = (UserPwdDTO) principals.getPrimaryPrincipal();
        //TODO 权限
        simpleAuthorizationInfo.addRoles(Lists.newArrayList());
        return simpleAuthorizationInfo;
    }
}
