package main.java.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @功能:
 * @作者:lgz
 * @日期:2022年8月16日
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
	String columnName();

	String type();

	int length();

	int smallLen() default 2;

	String checked() default "";
}
