package com.znsd.restaurant.servers.impl.meal;

import com.znsd.restaurant.bean.meal.MenuBean;
import com.znsd.restaurant.dao.impl.meal.MenuDaoImpl;
import com.znsd.restaurant.dao.meal.MenuDao;
import com.znsd.restaurant.servers.meal.MenuServers;

import java.util.List;

public class MenuServersImpl implements MenuServers {
	private MenuDao menuDao = new MenuDaoImpl();

	@Override
	public Boolean insert(MenuBean ben) {
		return menuDao.insert(ben);
	}

	@Override
	public Boolean update(MenuBean ben) {
		return menuDao.update(ben);
	}

	@Override
	public Boolean delete(int id) {
		return menuDao.delete(id);
	}

	@Override
	public List<MenuBean> query(MenuBean menu) {
		// TODO Auto-generated method stub
		return menuDao.query(menu);
	}

}
