package com.znsd.restaurant.servers.meal;

import com.znsd.restaurant.bean.meal.MenuBean;

import java.util.List;

public interface MenuServers {
	public String add(MenuBean ben);

	public void delete(String name);

	public List<MenuBean> query(int start, int pageSize);

	public void mod(String name, int id);

	public String select(String line, String pageSize, int first);

	public int getCount();

}
