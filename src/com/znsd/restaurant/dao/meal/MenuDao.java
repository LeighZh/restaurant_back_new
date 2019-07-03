package com.znsd.restaurant.dao.meal;

import com.znsd.restaurant.bean.meal.MenuBean;

import java.util.List;

public interface MenuDao {
	Boolean insert(MenuBean ben);

	Boolean update(MenuBean ben);

	Boolean delete(int id);

	List<MenuBean> query(MenuBean menu);

	boolean updateImage(int id, String path);

}
