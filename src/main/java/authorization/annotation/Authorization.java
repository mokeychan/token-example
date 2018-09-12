package authorization.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 检查用户是否登录的自定义注解
 * @Author: chenfeixiang
 * @Date: Created in 19:05 2018/9/11
 */
@Target(ElementType.METHOD) // 作用在方法上
@Retention(RetentionPolicy.RUNTIME)// 运行时注解
public @interface Authorization {
}
