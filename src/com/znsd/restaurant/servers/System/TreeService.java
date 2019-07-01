package com.znsd.restaurant.servers.System;

import com.znsd.restaurant.bean.system.AuthorityBean;

import java.util.List;

public interface TreeService {
	public List<AuthorityBean> queryAuthority(String name);
}
