package com.znsd.restaurant.servers.meal;

import com.znsd.restaurant.bean.meal.MenuBean;

import java.util.List;

public interface MenuServers {
	Boolean insert(MenuBean ben);

	Boolean update(MenuBean ben);

	Boolean delete(int id);

	List<MenuBean> query(MenuBean menu);

	boolean updateImage(int id, String path);

}
