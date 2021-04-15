package com.mrwang.happytime.musicserver.annotation;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ParameterChecks.class)
public @interface ParameterCheck {
    String value();
    Class type() default Object.class;
    boolean notNull() default true;
}
