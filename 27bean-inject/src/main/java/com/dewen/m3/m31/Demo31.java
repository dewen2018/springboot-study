package com.dewen.m3.m31;

import com.dewen.m3.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * 直接使用@Import导入person类，然后尝试从applicationContext中取，成功拿到
 * <p>
 * 在进行Spring扩展时经常会用到，它经常搭配自定义注解进行使用，然后往容器中导入一个配置文件。
 * 关于@Import注解，我会多介绍一点，它有四种使用方式。这是@Import注解的源码，表示只能放置在类上。
 **/
@Import(Person.class)
public class Demo31 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Demo31.class);
        Person bean = applicationContext.getBean(Person.class);
        System.out.println(bean);
    }
}