package com.znsd.restaurant.bean.system;
//角色权限
public class Role_AuthorityBean {
	private int roleId;//角色id
	private int authorityId;//权限id
	public Role_AuthorityBean() {
		super();
	}
	public Role_AuthorityBean(int roleId, int authorityId) {
		super();
		this.roleId = roleId;
		this.authorityId = authorityId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}
	@Override
	public String toString() {
		return "role_AuthorityId [roleId=" + roleId + ", authorityId=" + authorityId + "]";
	}
}
