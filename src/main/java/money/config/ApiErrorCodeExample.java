package money.config;

import money.core.BaseCode;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
public @interface ApiErrorCodeExample {
    Class<? extends BaseCode>[] value();
}
