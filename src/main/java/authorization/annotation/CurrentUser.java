package authorization.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 方法参数是使用该注解，会注入当前登录系统的User对象
 * @Author: chenfeixiang
 * @Date: Created in 19:07 2018/9/11
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentUser {
}
