package com.znsd.restaurant.bean;

import java.util.Date;

//评价
public class EvaluateBean {
	private int userId;//用户id
	private String menuName;//评价菜系名
	private String evaluateContent;//评价内容
	private Date evaluateTime;//评价时间
	public EvaluateBean() {
		super();
	}
	public EvaluateBean(int userId, String menuName, String evaluateContent, Date evaluateTime) {
		super();
		this.userId = userId;
		this.menuName = menuName;
		this.evaluateContent = evaluateContent;
		this.evaluateTime = evaluateTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getEvaluateContent() {
		return evaluateContent;
	}
	public void setEvaluateContent(String evaluateContent) {
		this.evaluateContent = evaluateContent;
	}
	public Date getEvaluateTime() {
		return evaluateTime;
	}
	public void setEvaluateTime(Date evaluateTime) {
		this.evaluateTime = evaluateTime;
	}
	@Override
	public String toString() {
		return "Evaluate [userId=" + userId + ", menuName=" + menuName + ", evaluateContent=" + evaluateContent
				+ ", evaluateTime=" + evaluateTime + "]";
	}
}
