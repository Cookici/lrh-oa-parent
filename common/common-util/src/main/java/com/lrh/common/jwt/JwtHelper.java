package com.lrh.common.jwt;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @ProjectName: lrh-oa-parent
 * @Package: com.lrh.common.jwt
 * @ClassName: JwtHelper
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/4/21 12:54
 */

public class JwtHelper {

    private static long tokenExpiration = 365 * 24 * 60 * 60 * 1000;
    private static String tokenSignKey = "123456";

    /**
     * 用户id和用户名称生成token字符串
     *
     * @param userId
     * @param username
     * @return
     */
    public static String createToken(Long userId, String username) {
        String token = Jwts.builder()
                .setSubject("AUTH-USER")        //分类
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))  //设置token有效时长
                .claim("userId", userId)        //设置主体部分
                .claim("username", username)    //设置主体部分
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)   //签名部分
                .compressWith(CompressionCodecs.GZIP)        //签名部分
                .compact();
        return token;
    }

    /**
     * 从生成的token字符串获取用户id
     *
     * @param token
     * @return
     */
    public static Long getUserId(String token) {
        try {
            if (StringUtils.isEmpty(token)) {
                return null;
            }
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            Integer userId = (Integer) claims.get("userId");
            return userId.longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从生成的token字符串获取用户名称
     *
     * @param token
     * @return
     */
    public static String getUserName(String token) {
        try {
            if (StringUtils.isEmpty(token)) {
                return "";
            }
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
