package com.mrwang.happytime.musicserver.inteceptor;

import com.mrwang.happytime.musicserver.util.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class LogInteceptor implements HandlerInterceptor {

    private final static Logger loggerFactory = LoggerFactory.getLogger(HandlerInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("尼玛没了");
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String ip = IpUtils.getIpAddr(request);
        loggerFactory.info("Ip:" + ip + " method:" + method + " uri:" + requestURI);
        return true;
    }
}
