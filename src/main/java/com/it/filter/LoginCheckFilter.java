package com.it.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.it.pojo.Result;
import com.it.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //获取url
        String url = req.getRequestURL().toString();
        log.info("获取的url:{}",url);
        //判断url中是否存在login的关键字
        log.info("登录");
        if (url.contains("login")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //获取请求头中的令牌
        String jwt = req.getHeader("token");
        //判断令牌是否存在
        if(!StringUtils.hasLength(jwt)){
            log.info("请求失败");
            Result error = Result.error("NOT_LOGIN");
            //手动转成成JSON
            String notLogin = JSONObject.toJSONString(error);
            //响应回浏览器
            resp.getWriter().write(notLogin);
            return;
        }
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析失败");
            Result error = Result.error("NOT_LOGIN");
            //手动转成成JSON
            String notLogin = JSONObject.toJSONString(error);
            //响应回浏览器
            resp.getWriter().write(notLogin);
            return;
        }
        //放行

        filterChain.doFilter(servletRequest,servletResponse);


    }
}
