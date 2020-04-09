package elec.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * 自定义注解，用于细颗粒权限
 */
//被这个注解修饰的注解，利用反射，将其他的注解读取出来
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationLimit {
	String mid();  //子模块模块名称
	String pid(); //父模块操作名称
}
