package com.mrwang.happytime.musicserver.filter;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class ShiroCorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
//        response.setHeader("Access-Control-Allow-Methods", "*");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        //response.setHeader("Access-Control-Allow-Headers", "accept,x-requested-with,Content-Type");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        //response.setHeader("Access-Control-Allow-Origin", "*");
//        if(httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())){
//            response.setStatus(HttpStatus.OK.value());
//            return;
//        }else{
            filterChain.doFilter(servletRequest,servletResponse);


    }
}
