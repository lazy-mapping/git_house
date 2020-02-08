package com.mybatis.vo;

public class Car extends Vehicle {
	private Integer doorCount;//车门数
	
	public Car() {}
	
	public Integer getDoorCount() {
		return doorCount;
	}
	public void setDoorCount(Integer doorCount) {
		this.doorCount = doorCount;
	}

	@Override
	public String toString() {
		return "Car [doorCount=" + doorCount + "]";
	}
	
}
