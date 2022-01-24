package com.dewen.m4;

import com.dewen.m3.Person;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FactoryBean接口和BeanFactory千万不要弄混了，
 * 从名字其实可以大概的区分开，FactoryBean, 后缀为bean，那么它其实就是一个bean,
 * BeanFactory，顾名思义 bean工厂，它是IOC容器的顶级接口，这俩接口都很重要。
 */
@Configuration
public class Demo4 {

    @Bean
    public PersonFactoryBean personFactoryBean() {
        return new PersonFactoryBean();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Demo4.class);
        Person bean = applicationContext.getBean(Person.class);
        System.out.println(bean);
    }
}

/**
 * 使用@Configuration + @Bean的方式将 PersonFactoryBean 加入到容器中，注意，我没有向容器中注入 Person,
 * 而是直接注入的 PersonFactoryBean 然后从容器中拿Person这个类型的bean。
 *
 * @return
 */
class PersonFactoryBean implements FactoryBean<Person> {

    /**
     * 直接new出来Person进行返回.
     */
    @Override
    public Person getObject() throws Exception {
        return new Person();
    }

    /**
     * 指定返回bean的类型.
     */
    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }
}