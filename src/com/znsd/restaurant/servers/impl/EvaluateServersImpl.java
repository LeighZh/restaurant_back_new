package com.znsd.restaurant.servers.impl;

import com.znsd.restaurant.dao.impl.EvaluateDaoImp;
import com.znsd.restaurant.servers.EvaluateServers;

public class EvaluateServersImpl implements EvaluateServers{

	@Override
	public String select(String line, String pageSize, int first) {
		return new EvaluateDaoImp().select(line, pageSize, first);
	}
		
}
