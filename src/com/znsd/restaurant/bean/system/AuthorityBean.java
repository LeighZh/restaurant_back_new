package com.znsd.restaurant.bean.system;
//权限
public class AuthorityBean {
	private String text;//权限名称
	private String listName;//列标题
	private String iconCls;//功能按钮图片
	private String name; //存员工或老板

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AuthorityBean(String text) {
		super();
		this.text = text;
	}
	public AuthorityBean() {
		// TODO 123465
	}
	public AuthorityBean(String text, String listName, String iconCls, String name) {
		super();
		this.text = text;
		this.listName = listName;
		this.iconCls = iconCls;
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public String getListName() {
		return listName;
	}
	public String getIconCls() {
		return iconCls;
	}
	@Override
	public String toString() {
		return "AuthorityBean [text=" + text + ", listName=" + listName + ", iconCls=" + iconCls + ", name=" + name
				+ "]";
	}
	
}
