package dewen;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 元注解就是作用在注解上的注解
 * @Retention 定义注解保留到什么阶段
 * RetentionPolicy.SOURCE	只在代码中保留，在字节码文件中就删除了
 * RetentionPolicy.CLASS	只在代码和字节码文件中都保留
 * RetentionPolicy.RUNTIME	所有阶段都保留
 *
 *
 * @Target 规定注解作用在什么上面
 * ElementType.TYPE	作用在类、接口等上面
 * ElementType.METHOD	作用在方法上面
 * ElementType.FIELD	作用在字段上面
 * @Documented
 * @Documented用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化。Documented是一个标记注解，没有成员。
 *
 * 2.4、@Inherited
 * @Inherited 元注解是一个标记注解，@Inherited阐述了某个被标注的类型是被继承的。如果一个使用了@Inherited修饰的annotation类型被用于一个class，
 * 则这个annotation将被用于该class的子类。
 * 注意：@Inherited annotation类型是被标注过的class的子类所继承。类并不从它所实现的接口继承annotation，方法并不从它所重载的方法继承annotation。
 * 当@Inherited annotation类型标注的annotation的Retention是RetentionPolicy.RUNTIME，则反射API增强了这种继 承性。
 * 如果我们使用java.lang.reflect去查询一个@Inherited annotation类型的annotation时，
 * 反射代码检查将展开工作：检查class和其父类，直到发现指定的annotation类型被发现， 或者到达类继承结构的顶层。
 *
 * @Deprecated,表示这个方法过时了,最好别用了  @Override表示方法重写，或者覆盖。
 * @SuppressWarnings让编译器忽略警告信息。 （@suppressWarnings({rawtypes})）
 * @suppressWarnings(value = {rawtypes})//泛型
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    public String value();

    // 定义一个注解属性age
//    public int age();
//    public String[] names();
}
