package com.sean.android.github.model;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Seonil on 2017-01-26.
 */

@Retention(RUNTIME)
@Target(METHOD)
public @interface Header {
    String value();
}
