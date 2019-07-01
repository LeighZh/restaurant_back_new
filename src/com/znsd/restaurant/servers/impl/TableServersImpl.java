package com.znsd.restaurant.servers.impl;

import com.znsd.restaurant.bean.TableZibean;
import com.znsd.restaurant.dao.TableDao;
import com.znsd.restaurant.dao.impl.TableDaoImpl;
import com.znsd.restaurant.servers.TableServers;

import java.util.List;

public class TableServersImpl implements TableServers {
	TableDao tabl = new TableDaoImpl();
	@Override
	public List<TableZibean> main(String page, String pageSize) {
		// TODO Auto-generated method stub
		List<TableZibean> list = tabl.main(page,pageSize);
		return list;
	}
	@Override
	public String count() {
		// TODO Auto-generated method stub
		return tabl.count();
	}
	@Override
	public void add(String id, String userName) {
		// TODO Auto-generated method stub
		this.tabl.add(id,userName);
	}
	@Override
	public void delete(String id, String userName) {
		// TODO Auto-generated method stub
		this.tabl.delete(id,userName);
	}
	@Override
	public List<TableZibean> main2(String page, String pageSize) {
		// TODO Auto-generated method stub
		List<TableZibean> list = tabl.main2(page,pageSize);
		return list;
	}
	@Override
	public void remove(String id, String userName) {
		// TODO Auto-generated method stub
		this.tabl.remove(id,userName);
	}
	@Override
	public void board(String name,String id) {
		// TODO Auto-generated method stub
		this.tabl.board(name,id);
	}
	@Override
	public void increase(String name, String line, String row) {
		// TODO Auto-generated method stub
		this.tabl.increase(name,line,row);
	}

	

}
