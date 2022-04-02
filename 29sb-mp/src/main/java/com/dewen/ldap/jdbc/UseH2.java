package com.dewen.ldap.jdbc;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UseH2 {

	boolean value() default true;

	boolean rollback() default true;

}
