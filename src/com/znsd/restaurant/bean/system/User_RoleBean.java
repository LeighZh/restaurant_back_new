package com.znsd.restaurant.bean.system;
//用户角色
public class User_RoleBean {
	private int userId;//用户id
	private int roleId;//角色id
	public User_RoleBean() {
		super();
	}
	public User_RoleBean(int userId, int roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "user_Role [userId=" + userId + ", roleId=" + roleId + "]";
	}
}
