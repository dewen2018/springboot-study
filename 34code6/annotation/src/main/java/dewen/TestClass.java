package dewen;

public class TestClass {
    @MyAnnotation("nihao")
    public void run(){
        System.out.println("I am run method");
    }

    @MyAnnotation("nihao")
    public void jump(){
        System.out.println("I am jump method");
    }
}
