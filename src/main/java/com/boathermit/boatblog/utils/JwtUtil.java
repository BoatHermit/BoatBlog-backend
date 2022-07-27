package com.boathermit.boatblog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.boathermit.boatblog.enums.ROLE;
import com.boathermit.boatblog.enums.ResultCode;
import com.boathermit.boatblog.exception.MyServiceException;
import com.boathermit.boatblog.model.po.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * @author Yin Zihang
 * @since 2022/7/27 14:01
 */
@Component
@Data
public class JwtUtil {

    private static String secret;

    private static long expire;

    private Key key;

    @Value("${jwt.secret}")
    public void setJwtSecret(String jwtSecret) { //通过set让static方法读取配置文件中的值
        JwtUtil.secret = jwtSecret;
    }

    @Value("${jwt.expire}")
    public void setExpire(long expire) { //通过set让static方法读取配置文件中的值
        JwtUtil.expire = expire;
    }


    /**
     * 签发jwt
     *
     * @param user 用户对象
     * @return token
     */
    public static String createJwt(User user) {
        Date date = new Date();
        Date expireDate = new Date(date.getTime() + expire);

        return JWT.create()
                //可以将基本信息放到claims中
                .withClaim("account", user.getAccount())
                //name
                .withClaim("role", user.getRole().name())
                //超时设置,设置过期的日期
                .withExpiresAt(expireDate)
                //签发时间
                .withIssuedAt(new Date())
                //SECRET加密
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 解析JWT
     *
     * @param token token
     * @return 解析结果
     */
    public static Map<String, Claim> parseJwt(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims();
        } catch (TokenExpiredException e) {
            throw new MyServiceException(ResultCode.TOKEN_FAILED);
        }
    }
}