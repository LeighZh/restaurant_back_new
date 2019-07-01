package com.znsd.restaurant.dao;

import com.znsd.restaurant.bean.meal.MenuBean;

import java.util.List;

public interface OrderdishesMenuDao {
	public List<MenuBean> getSelectMenu(int id, String user);
	public void  evaluate(String userId, String menuName, String evaluateStatus, String text);
}
