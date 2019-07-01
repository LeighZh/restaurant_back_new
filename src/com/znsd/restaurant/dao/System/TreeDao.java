package com.znsd.restaurant.dao.System;

import com.znsd.restaurant.bean.system.AuthorityBean;

import java.util.List;

public interface TreeDao {
	public List<AuthorityBean> queryAuthority(String name);
}
