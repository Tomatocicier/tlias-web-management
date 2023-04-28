package com.it.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    @Override //初始化方法，只调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init初始化执行了");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到了");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override //销毁的方法，调用多次
    public void destroy() {
        System.out.println("销毁方法");
    }
}
