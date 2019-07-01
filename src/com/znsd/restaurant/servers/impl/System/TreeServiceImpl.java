package com.znsd.restaurant.servers.impl.System;

import com.znsd.restaurant.bean.system.AuthorityBean;
import com.znsd.restaurant.dao.System.TreeDao;
import com.znsd.restaurant.dao.impl.System.TreeDaoImpl;
import com.znsd.restaurant.servers.System.TreeService;

import java.util.List;

public class TreeServiceImpl implements TreeService{

	private TreeDao treeDao = new TreeDaoImpl();
	@Override
	public List<AuthorityBean> queryAuthority(String name) {
		return treeDao.queryAuthority(name);
	}
	
}
