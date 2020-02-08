package com.mybatis.vo;

public class Suv extends Vehicle {
	private Boolean allWheelDrive;//全轮驱动awd
	
	public Suv() {}
	
	public Boolean getAllWheelDrive() {
		return allWheelDrive;
	}
	public void setAllWheelDrive(Boolean allWheelDrive) {
		this.allWheelDrive = allWheelDrive;
	}

	@Override
	public String toString() {
		return "Suv [allWheelDrive=" + allWheelDrive + "]";
	}
}
