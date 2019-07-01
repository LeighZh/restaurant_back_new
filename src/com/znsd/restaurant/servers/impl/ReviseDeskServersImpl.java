package com.znsd.restaurant.servers.impl;

import com.znsd.restaurant.dao.impl.ReviseDeskDnoImpl;
import com.znsd.restaurant.servers.ReviseDeskServers;

public class ReviseDeskServersImpl implements ReviseDeskServers {

	@Override
	public String select(String line, String pageSize, int first) {
		return new ReviseDeskDnoImpl().select(line, pageSize, first);
	}

}
