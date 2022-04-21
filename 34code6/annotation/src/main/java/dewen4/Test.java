package dewen4;

@Dewen
public class Test {
    public static void showDewen(Class c) {
        System.out.println(c.getName());
        boolean isExist = c.isAnnotationPresent(Dewen.class);
        if(isExist){
            Dewen dewen= (Dewen) c.getAnnotation(Dewen.class);
            System.out.println(dewen.name());
        }
    }

    public static void main(String[] args) {
        Test.showDewen(Test.class);
    }
}
