package com.dewen.m2;

import com.dewen.m2.bean.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 比较多的方式，@Componet中文译为组件，放在类名上面，然后@ComponentScan放置在我们的配置类上，
 * 然后可以指定一个路径，进行扫描带有@Componet注解的bean，然后加至容器中。
 */
@ComponentScan(basePackages = "com.dewen.m2.bean.*")
public class Demo2 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Demo2.class);
        Person bean = applicationContext.getBean(Person.class);
        System.out.println(bean);
    }
}