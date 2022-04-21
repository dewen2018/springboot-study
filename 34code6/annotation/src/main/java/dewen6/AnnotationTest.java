package dewen6;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@MyAnTargetType
public class AnnotationTest {
    @MyAnTargetField
    public String field = "我是字段";

    @MyAnTargetMethod("测试方法")
    public void test(@MyAnTargetParameter String args){
        System.out.println("参数值=="+args);
    }

    public static void main(String[] args) {
        MyAnTargetType t = AnnotationTest.class.getAnnotation(MyAnTargetType.class);
        System.out.println("类上的注解值==="+t.value());
        MyAnTargetMethod tm = null;
        try {
            // 根据反射获取AnnotationTest类上的test方法
            Method method = AnnotationTest.class.getDeclaredMethod("test",String.class);
            // 获取方法上的注解MyAnTargetMethod
            tm=method.getAnnotation(MyAnTargetMethod.class);
            System.out.println("方法上的注解值 === "+tm.value());
            // 获取方法上的所有参数注解  循环所有注解找到MyAnTargetParameter注解
            Annotation[][] annotations = method.getParameterAnnotations();
            for (Annotation[] tt:annotations){
                for (Annotation annotation : tt) {
                    if(annotation instanceof MyAnTargetParameter){
                        System.out.println("参数上的注解值 === "+((MyAnTargetParameter) annotation).value());
                    }
                }
            }
            method.invoke(new AnnotationTest(),"改变默认参数");
            MyAnTargetField fieldAn = AnnotationTest.class.getDeclaredField("field").getAnnotation(MyAnTargetField.class);
            System.out.println("字段上的注解值 === "+fieldAn.value());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
