package com.pycogroup.taotran.springbootmongosec.client.config.custom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface EnableAuthentication {

    boolean adminAuth() default false;
}
