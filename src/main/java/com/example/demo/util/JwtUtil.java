package com.example.demo.util;

import cn.hutool.jwt.JWT;
import com.example.demo.config.JwtProperties;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultJwtBuilder;

import java.util.Date;

/**
 * @Author zl
 * @CreateTime 2022-09-16 10:40:28
 * @Description Jwt工具类
 */
public class JwtUtil {

    private static JwtProperties jwtProperties;

    static {
        JwtUtil.jwtProperties = SpringUtil.getBean(JwtProperties.class);
    }

    public static String createJwtToken(String subject) throws Exception {
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpireTime()))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    public static String parseJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
