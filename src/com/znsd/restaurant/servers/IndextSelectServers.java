package com.znsd.restaurant.servers;

public interface IndextSelectServers {
	public String select(String line, String pageSize, int first);
	public int count();
}
