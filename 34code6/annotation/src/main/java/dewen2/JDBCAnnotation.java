package dewen2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JDBCAnnotation {
    public String DriverClass() default "com.mysql.cj.jdbc.Driver";
    public String url() default "jdbc:mysql://localhost:3307/dewen";
    public String user() default "root";
    public String password();
}
