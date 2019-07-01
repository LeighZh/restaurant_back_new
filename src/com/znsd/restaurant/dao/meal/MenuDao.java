package com.znsd.restaurant.dao.meal;

import com.znsd.restaurant.bean.meal.MenuBean;

import java.util.List;

public interface MenuDao {
	public Boolean insert(MenuBean ben);

	public Boolean update(MenuBean ben);

	public Boolean delete(int id);

	public List<MenuBean> query(MenuBean menu);

}
