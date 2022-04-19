package dewen6;

public class ChildAnnotationTest extends AnnotationTest {
    public static void main(String[] args) {
        MyAnTargetType t = ChildAnnotationTest.class.getAnnotation(MyAnTargetType.class);
        System.out.println("类上的注解值 === "+t.value());
    }
}
