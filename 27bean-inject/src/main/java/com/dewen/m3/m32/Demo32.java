package com.dewen.m3.m32;

import com.dewen.m3.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

@Import(MyImportSelector.class)
public class Demo32 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Demo32.class);
        Person bean = applicationContext.getBean(Person.class);
        System.out.println(bean);
    }
}

/**
 * 自定义了一个 MyImportSelector 实现了 ImportSelector 接口，重写selectImports 方法，然后将我们要导入的类的全限定名写在里面即可
 */
class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.dewen.m3.Person"};
    }
}