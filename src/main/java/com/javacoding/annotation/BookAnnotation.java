package com.javacoding.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BookAnnotation {
	/**
	 * 书籍编号
	 * @return
	 */
	public String id() default "";
	
	/**
	 * 书籍名称 
	 */
	public String bookName() default "";
	/**
	 * 书籍价格
	 * @return
	 */
	public String price()	default "";
	/**
	 * 书籍作者
	 * @return
	 */
	public String bookAuthor() default "";
	/**
	 * 导出的excel列名
	 * @return
	 */
	public String name();
}
