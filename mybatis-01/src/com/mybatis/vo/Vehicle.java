package com.mybatis.vo;

public abstract class Vehicle {
	private Integer id;
	private String make;
	
	public Vehicle() {}  
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
}
