package com.znsd.restaurant.dao.meal;

import com.znsd.restaurant.bean.meal.MenuBean;

import java.util.List;

public interface MenuDao {
	public String add(MenuBean ben);

	public void delete(String name);

	public List<MenuBean> query(MenuBean menu);

	public void mod(String name, int id);

	public String select(String line, String pageSize, int first);

	public int count();

}
