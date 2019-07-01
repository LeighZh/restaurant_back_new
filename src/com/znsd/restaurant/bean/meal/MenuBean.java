package com.znsd.restaurant.bean.meal;

import java.util.Date;

//菜谱
public class MenuBean {
	private int mealId;//菜品id
	private String mealName;//菜品名称
	private double mealPrice;//价格
	public int mealSeriesId;//菜系id
	private String seriesName;//菜系名称
	private String mealImage;//图片地址
	private String mealDescription;//描述
	private String mealSummarize;//摘要
	public MenuBean(int mealId, String mealName, double mealPrice, int mealSeriesId, String mealImage, String mealDescription, String mealSummarize) {
		super();
		this.mealId = mealId;
		this.mealName = mealName;
		this.mealPrice = mealPrice;
		this.mealSeriesId = mealSeriesId;
		this.mealImage = mealImage;
		this.mealDescription=mealDescription;
		this.mealSummarize=mealSummarize;
	}
	public MenuBean() {
		super();
	}

	public MenuBean(int mealId, String mealName, double mealPrice, int mealSeriesId, String seriesName, String mealImage, String mealDescription, String mealSummarize) {
		this.mealId = mealId;
		this.mealName = mealName;
		this.mealPrice = mealPrice;
		this.mealSeriesId = mealSeriesId;
		this.seriesName = seriesName;
		this.mealImage = mealImage;
		this.mealDescription = mealDescription;
		this.mealSummarize = mealSummarize;
	}

	public MenuBean(int mealId, String mealName, double mealPrice, int mealSeriesId, String mealDescription, String mealSummarize) {
		this.mealId = mealId;
		this.mealName = mealName;
		this.mealPrice = mealPrice;
		this.mealSeriesId = mealSeriesId;
		this.mealDescription = mealDescription;
		this.mealSummarize = mealSummarize;
	}

	public MenuBean(int mealId, String mealName, double mealPrice, String seriesName, String mealImage, String mealSummarize) {
		this.mealId = mealId;
		this.mealName = mealName;
		this.mealPrice = mealPrice;
		this.seriesName = seriesName;
		this.mealImage = mealImage;
		this.mealSummarize = mealSummarize;
	}

	public MenuBean(String mealName, double mealPrice, String seriesName, String mealDescription) {
		super();
		this.mealName = mealName;
		this.mealPrice = mealPrice;
		this.seriesName = seriesName;
		this.mealDescription = mealDescription;
	}

	public MenuBean(int mealId, String mealName, int mealSeriesId) {
		this.mealId = mealId;
		this.mealName = mealName;
		this.mealSeriesId = mealSeriesId;
	}

	public int getMealId() {
		return mealId;
	}

	public void setMealId(int mealId) {
		this.mealId = mealId;
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public double getMealPrice() {
		return mealPrice;
	}

	public void setMealPrice(double mealPrice) {
		this.mealPrice = mealPrice;
	}

	public int getMealSeriesId() {
		return mealSeriesId;
	}

	public void setMealSeriesId(int mealSeriesId) {
		this.mealSeriesId = mealSeriesId;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getMealImage() {
		return mealImage;
	}

	public void setMealImage(String mealImage) {
		this.mealImage = mealImage;
	}

	public String getMealDescription() {
		return mealDescription;
	}

	public void setMealDescription(String mealDescription) {
		this.mealDescription = mealDescription;
	}

	public String getMealSummarize() {
		return mealSummarize;
	}

	public void setMealSummarize(String mealSummarize) {
		this.mealSummarize = mealSummarize;
	}

	//	public MenuBean(int menuId, String menuName, double price, String vegetableName, String picture, int userCount, int good,
//			int general, int bad, Date addTime,String menuType,String describe) {
//		super();
//		this.menuId = menuId;
//		this.menuName = menuName;
//		this.price = price;
//		this.vegetableName = vegetableName;
//		this.picture = picture;
//		this.userCount = userCount;
//		this.good = good;
//		this.general = general;
//		this.bad = bad;
//		this.addTime = addTime;
//		this.menuType = menuType;
//		this.describe=describe;
//	}
//	public String getMenuType() {
//		return menuType;
//	}
//	public void setMenuType(String menuType) {
//		this.menuType = menuType;
//	}
//	public int getUserCount() {
//		return userCount;
//	}
//	public void setUserCount(int userCount) {
//		this.userCount = userCount;
//	}
//	public int getGood() {
//		return good;
//	}
//	public void setGood(int good) {
//		this.good = good;
//	}
//	public int getGeneral() {
//		return general;
//	}
//	public void setGeneral(int general) {
//		this.general = general;
//	}
//	public int getBad() {
//		return bad;
//	}
//	public void setBad(int bad) {
//		this.bad = bad;
//	}
//	public Date getAddTime() {
//		return addTime;
//	}
//	public void setAddTime(Date addTime) {
//		this.addTime = addTime;
//	}

	@Override
	public String toString() {
		return "MenuBean{" +
				"mealId=" + mealId +
				", mealName='" + mealName + '\'' +
				", mealPrice=" + mealPrice +
				", mealSeriesId=" + mealSeriesId +
				", seriesName='" + seriesName + '\'' +
				", mealImage='" + mealImage + '\'' +
				", mealDescription='" + mealDescription + '\'' +
				", mealSummarize='" + mealSummarize + '\'' +
				'}';
	}
}
