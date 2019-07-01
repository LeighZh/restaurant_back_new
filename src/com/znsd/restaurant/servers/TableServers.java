package com.znsd.restaurant.servers;

import com.znsd.restaurant.bean.TableZibean;

import java.util.List;

public interface TableServers {
	public List<TableZibean> main(String page, String pageSize);
	public void add(String id, String userName);
	public void delete(String id, String userName);
	public String count();
	public List<TableZibean> main2(String page, String pageSize);
	public void remove(String id, String userName);
	public void board(String name, String id);
	public void increase(String name, String line, String row);
}
