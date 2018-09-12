package com.cfx.authorization.resolver;

import com.cfx.authorization.annotation.CurrentUser;
import com.cfx.common.Constants;
import com.cfx.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import com.cfx.repository.UserRepository;

/**
 * @Description: 将使用@CurrentUser注解的参数，注入当前登录的用户
 * @Author: chenfeixiang
 * @Date: Created in 10:47 2018/9/12
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    UserRepository userRepository;

    /**
     * 判断是否支持要转换的参数类型
     * @param parameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 如果使用CurrentUser注解并且参数类型是User则支持
        if(parameter.hasMethodAnnotation(CurrentUser.class) &&
                parameter.getParameterType().isAssignableFrom(User.class)) {
           return true;
        }
        return false;
    }

    /**
     * 如果支持后，进行相应的转换
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 取出鉴权时存入的登录用户Id
        Long currentUserId = (Long)webRequest.getAttribute(Constants.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
        if(null != currentUserId) {
            return userRepository.findById(currentUserId);
        }
        throw new MissingServletRequestPartException(Constants.CURRENT_USER_ID);
    }
}
