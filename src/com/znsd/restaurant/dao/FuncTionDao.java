package com.znsd.restaurant.dao;

import com.znsd.restaurant.bean.system.AuthorityBean;

import java.util.ArrayList;

public interface FuncTionDao {
	public ArrayList<AuthorityBean> select(String name);
}
