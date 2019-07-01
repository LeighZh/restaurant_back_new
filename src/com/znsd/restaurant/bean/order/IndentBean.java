package com.znsd.restaurant.bean.order;

//订单(子订单)
public class IndentBean {
	private int indentId;//下单id
	private String userName;//下单用户
	private String mealName;//商品名称
	private String mealImage;//商品图片
	private double money;//单价
	private  int count; //数量
	private double sumMoney;//总金额

	public IndentBean(int indentId, String mealName, String mealImage, double money, int count,double sumMoney) {
		this.indentId = indentId;
		this.mealName = mealName;
		this.mealImage = mealImage;
		this.money = money;
		this.sumMoney = sumMoney;
	}

	public int getIndentId() {
		return indentId;
	}

	public void setIndentId(int indentId) {
		this.indentId = indentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public String getMealImage() {
		return mealImage;
	}

	public void setMealImage(String mealImage) {
		this.mealImage = mealImage;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(double sumMoney) {
		this.sumMoney = sumMoney;
	}

	@Override
	public String toString() {
		return "IndentBean{" +
				"indentId=" + indentId +
				", userName='" + userName + '\'' +
				", mealName='" + mealName + '\'' +
				", mealImage='" + mealImage + '\'' +
				", money=" + money +
				", count=" + count +
				", sumMoney=" + sumMoney +
				'}';
	}
}
