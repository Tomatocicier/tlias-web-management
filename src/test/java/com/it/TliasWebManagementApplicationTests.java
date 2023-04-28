package com.it;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class TliasWebManagementApplicationTests {
    @Test
    public void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","tom");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "itxiaoli")//设置签名算法
                .setClaims(claims)//自定义部分
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置有效期
                .compact();
        System.out.println(jwt);
    }

    /**
     * 解析令牌
     */
    @Test
    public void testParseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("itxiaoli")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTY4MTM3NTQwMH0.DPWKj_23DaHP57A_JFyJYb-yzLOKEkMfRCvh30Mahmw")
                .getBody();
        System.out.println(claims);

    }
}
