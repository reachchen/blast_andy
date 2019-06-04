package com.yhbc.base.net;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 新接口中方法键值配置 <br>
 * YKey和{@link retrofit2.http.POST}、{@link retrofit2.http.GET}等方法互斥，只能出现一个；
 *
 * @author xuhaijiang on 2018/10/9.
 */
@Documented
@Target({METHOD})
@Retention(RUNTIME)
public @interface YKey {

    String value();

    String defaultValue();

    String httpMethod() default "POST";

    boolean hasBody() default true;
}
