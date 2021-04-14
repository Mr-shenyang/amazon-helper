package com.oscoder.amazon.helper.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.oscoder.amazon.helper.user.api.dto.UserPwdDTO;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JWTCredentialsMatcher implements CredentialsMatcher {

    private final Logger log = LoggerFactory.getLogger(JWTCredentialsMatcher.class);

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        String token = (String) authenticationToken.getCredentials();
        Object stored = authenticationInfo.getCredentials();
        String salt = stored.toString();

        UserPwdDTO user = (UserPwdDTO)authenticationInfo.getPrincipals().getPrimaryPrincipal();
        try {
            Algorithm algorithm = Algorithm.HMAC256(salt);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", user.getName())
                    .withClaim("userid", user.getId())
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            log.error("Token Error:{}", e.getMessage());
        }

        return false;
    }

}