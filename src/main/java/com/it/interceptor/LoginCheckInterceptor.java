package com.it.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.it.pojo.Result;
import com.it.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override //在目标资源方法运行前运行，返回值为true放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        //获取url
        String url = req.getRequestURL().toString();
        log.info("获取的url:{}", url);
        //判断url中是否存在login的关键字
        log.info("登录");
        if (url.contains("login")) {
            return true;
        }
        //获取请求头中的令牌
        String jwt = req.getHeader("token");
        //判断令牌是否存在
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求失败");
            Result error = Result.error("NOT_LOGIN");
            //手动转成成JSON
            String notLogin = JSONObject.toJSONString(error);
            //响应回浏览器
            resp.getWriter().write(notLogin);
            return false;
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
            return false;
        }
        //放行
        log.info("放行");
        return true;
    }

    @Override //在目标资源运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle运行了.....");

    }

    @Override //识图渲染完毕后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion运行了...");
    }
}

