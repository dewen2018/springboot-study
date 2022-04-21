package dewen;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyAnnotationExample {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        //获取TestClass的字节码文件
        Class clazz = TestClass.class;

        //获取所有的方法
        Method[] methods=clazz.getMethods();

        //遍历所有的方法
        for (Method method : methods) {
            //判断方法是否有指定注解
            Boolean flag = method.isAnnotationPresent(MyAnnotation.class);
            //如果有MyAnnotation注解，就执行方法
            if(flag)
                method.invoke(clazz.newInstance());
        }
    }

}
