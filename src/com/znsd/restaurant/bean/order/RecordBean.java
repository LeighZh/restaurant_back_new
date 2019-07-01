package com.znsd.restaurant.bean.order;

//消费记录(主订单)
public class RecordBean {
	private int recordId;
	private String userName;//用户名称
	private String state;//订单状态
	private double consumptionMoney;//消费金额
	private String time;//消费时间

	public RecordBean(int recordId, String userName, String state, double consumptionMoney, String time) {
		this.recordId = recordId;
		this.userName = userName;
		this.state = state;
		this.consumptionMoney = consumptionMoney;
		this.time = time;
	}

	public RecordBean(int recordId, String userName, String time) {
		this.recordId = recordId;
		this.userName = userName;
		this.time = time;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getConsumptionMoney() {
		return consumptionMoney;
	}

	public void setConsumptionMoney(double consumptionMoney) {
		this.consumptionMoney = consumptionMoney;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "RecordBean{" +
				"recordId=" + recordId +
				", userName='" + userName + '\'' +
				", state='" + state + '\'' +
				", consumptionMoney=" + consumptionMoney +
				", time='" + time + '\'' +
				'}';
	}
}
