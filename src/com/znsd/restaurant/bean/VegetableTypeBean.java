package com.znsd.restaurant.bean;

import java.util.Date;

//菜系
public class VegetableTypeBean {
	public int getSign() {
		return sign;
	}
	public void setSign(int sign) {
		this.sign = sign;
	}
	private int vegetableId;//菜系id
	private String vegetableName;//菜系名称
	private Date addTime;//增加时间
	private int sign;//删除标记   1代表未删除  2代表已删除
	
	public VegetableTypeBean() {
		super();
	}
	
	public VegetableTypeBean(int vegetableId, String vegetableName) {
		super();
		this.vegetableId = vegetableId;
		this.vegetableName = vegetableName;
	}
	public VegetableTypeBean(int vegetableId, String vegetableName, Date addTime) {
		super();
		this.vegetableId = vegetableId;
		this.vegetableName = vegetableName;
		this.addTime = addTime;
	}
	public VegetableTypeBean(int vegetableId, String vegetableName, Date addTime, int sign) {
		super();
		this.vegetableId = vegetableId;
		this.vegetableName = vegetableName;
		this.addTime = addTime;
		this.sign = sign;
	}
	public int getVegetableId() {
		return vegetableId;
	}
	public void setVegetableId(int vegetableId) {
		this.vegetableId = vegetableId;
	}
	public String getVegetableName() {
		return vegetableName;
	}
	public void setVegetableName(String vegetableName) {
		this.vegetableName = vegetableName;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	@Override
	public String toString() {
		return "VegetableType [vegetableId=" + vegetableId + ", vegetableName=" + vegetableName + ", addTime=" + addTime
				+ "]";
	}
}
