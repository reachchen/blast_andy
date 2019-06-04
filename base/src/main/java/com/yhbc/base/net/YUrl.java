package com.yhbc.base.net;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * retrofit接口注解
 * Created by xuhaijiang on 2018/4/17.
 */
@Documented
@Target({TYPE, METHOD})
@Retention(RUNTIME)
public @interface YUrl {
    /**
     * @return 对应的url
     */
    UrlEnum value();

//    /**
//     * 是否在方法上有该注解
//     *
//     * @return true:在方法上有注解;false:默认,减少反射
//     */
//    boolean hasInMethod() default false;

}
