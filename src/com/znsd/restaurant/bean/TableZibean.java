package com.znsd.restaurant.bean;

public class TableZibean {
	private int tableId;
	private String tableName;
	private String tableStatus;
	private int line;
	private int rowa;
	private String userName;
	private String createTime;
	public TableZibean() {
		super();
	}
	public TableZibean(int tableId, String tableName, String tableStatus, int line, int rowa,String userName,String createTime) {
		super();
		this.tableId = tableId;
		this.tableName = tableName;
		this.tableStatus = tableStatus;
		this.line = line;
		this.rowa = rowa;
		this.userName = userName;
		this.createTime = createTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableStatus() {
		return tableStatus;
	}
	public void setTableStatus(String tableStatus) {
		this.tableStatus = tableStatus;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public int getRowa() {
		return rowa;
	}
	public void setRowa(int rowa) {
		this.rowa = rowa;
	}
	@Override
	public String toString() {
		return "TableZi [tableId=" + tableId + ", tableName=" + tableName + ", tableStatus=" + tableStatus + ", line="
				+ line + ", row=" + rowa + "]";
	}
}
