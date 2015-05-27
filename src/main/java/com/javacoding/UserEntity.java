package com.javacoding;

import java.io.Serializable;

public class UserEntity implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7086027127379288125L;
	
	private String id="222";
	private String name;
	private String sex;
	private String scope;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
		System.out.println(id);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public UserEntity(String id, String name, String sex, String scope) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.scope = scope;
	}
	public UserEntity() {
		System.out.println("实例化了。。。");
	}
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", scope=" + scope + "]";
	}
}
