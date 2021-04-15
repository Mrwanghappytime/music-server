package com.mrwang.happytime.musicserver.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MybatisMap {
    String value() default "";
}
