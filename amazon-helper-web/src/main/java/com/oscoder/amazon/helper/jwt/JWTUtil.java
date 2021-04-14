package com.oscoder.amazon.helper.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class JWTUtil {
    private static final String USER_NAME = "username";
    private static final String USER_Id = "userid";
    // 过期时间5分钟
    private static final long EXPIRE_TIME = 5*60*1000;

    private static final int tokenRefreshInterval = 200;
    // 密钥
    private static final String SECRET = "&ds*SD(isd&Jd^67;";

    /**
     * 生成签名,5min后过期
     * @param username 用户名
     * @return 加密的token
     */
    public static String sign(String username,Integer userId) {
        Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 附带username信息
        return JWT.create()
                .withClaim(USER_NAME, username)
                .withClaim(USER_Id, userId)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 校验token是否正确
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token, String username,Integer userId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(USER_NAME, username)
                    .withClaim(USER_Id, userId)
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            log.error("verify error from {}",token,e);
            return false;
        }
    }
    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的签发时间
     */
    public static Date getIssuedAt(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getIssuedAt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static  boolean shouldTokenRefresh(String token){
        LocalDateTime issueTime = LocalDateTime.ofInstant(getIssuedAt(token).toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().minusSeconds(tokenRefreshInterval).isAfter(issueTime);
    }

    /**
     * token是否过期
     * @return true：过期
     */
    public static boolean isTokenExpired(String token) {
        Date now = Calendar.getInstance().getTime();
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().before(now);
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUserName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(USER_NAME).asString();
        } catch (JWTDecodeException e) {
            log.error("getUserName error from {}",token,e);
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static Integer getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(USER_Id).asInt();
        } catch (JWTDecodeException e) {
            log.error("getUserId error from {}",token,e);
            return null;
        }
    }
}