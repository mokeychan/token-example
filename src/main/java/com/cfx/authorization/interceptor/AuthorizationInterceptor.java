package com.cfx.authorization.interceptor;

import com.cfx.authorization.annotation.Authorization;
import com.cfx.authorization.manager.TokenManager;
import com.cfx.common.Constants;
import com.cfx.common.TokenModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Description: 自定义拦截器，用来判断请求是否有权限
 * @Author: chenfeixiang
 * @Date: Created in 10:10 2018/9/12
 */
@Component
@Slf4j
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    TokenManager tokenManager;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        log.info("into preHandle..");

        // 如果请求不是映射到方法，就直接通过
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 从request请求header中获取认证信息
        String authorization = request.getHeader(Constants.AUTHORIZATION);
        // 通过认证信息来获取token
        TokenModel tokenModel = tokenManager.getToken(authorization);
        // 认证token
        if(tokenManager.checkToken(tokenModel)) {
            // 如果认证成功，将该token对应的用户id存入request请求中
            request.setAttribute(Constants.CURRENT_USER_ID, tokenModel.getToken());
        }
        // 如果认证失败并且方法注明了Authorization，返回401
        if(method.getAnnotation(Authorization.class) != null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }
}
