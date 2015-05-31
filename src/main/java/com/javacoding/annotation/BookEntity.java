package com.javacoding.annotation;

/**
 * 书籍实体
 * @author JM
 */
public class BookEntity {
	
	@BookAnnotation(name="书籍编号")
	private int id;
	
	@BookAnnotation(name = "书籍名称")
	private String bookName;
	
	@BookAnnotation(name="售价")
	private float price;
	
	@BookAnnotation(name="作者")
	private String bookAuthor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
}
