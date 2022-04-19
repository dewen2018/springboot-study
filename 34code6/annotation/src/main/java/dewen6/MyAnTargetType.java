package dewen6;

import java.lang.annotation.*;

/**
 * 接口枚举类上
 */
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnTargetType {
    public String value() default "我是定义在类接口枚举类上的注解元素value的默认值";
}
