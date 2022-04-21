package dewen2;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    @JDBCAnnotation(user = "root",password = "root")
    public static Connection getConnection() throws NoSuchMethodException, ClassNotFoundException, SQLException {
        Class clazz = JDBCUtils.class;

        //获取getConnection对应的方法
        Method method = clazz.getMethod("getConnection");

        //判断是否包含@JDBCAnnotation注解
        if (method.isAnnotationPresent(JDBCAnnotation.class)){
            //通过method获取注解值
            JDBCAnnotation annotation = method.getAnnotation(JDBCAnnotation.class);
            String driverClass = annotation.DriverClass();
            String url = annotation.url();
            String user = annotation.user();
            String password = annotation.password();

            //注册驱动
            Class.forName(driverClass);
            //获取连接
            Connection connection = DriverManager.getConnection(url,user,password);
            return connection;
        }
        return  null;
    }

    public static void main(String[] args) throws NoSuchMethodException, SQLException, ClassNotFoundException {
        Connection connection=JDBCUtils.getConnection();
    }
}
