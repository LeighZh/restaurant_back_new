package com.znsd.restaurant.servers.impl.order;

import com.znsd.restaurant.bean.order.IndentBean;
import com.znsd.restaurant.dao.order.IndentDao;
import com.znsd.restaurant.dao.impl.order.IndentDaoImpl;
import com.znsd.restaurant.servers.order.IndentServers;

import java.util.List;

/**
**oyb
**2018年7月27日
**/
public class IndentServersImpl implements IndentServers{
	IndentDao inde = new IndentDaoImpl();
	@Override
	public List<IndentBean> getTheOrder(int id) {
		// TODO Auto-generated method stub
		List<IndentBean> list = inde.getTheOrder(id);
		return list;
	}

	@Override
	public String count(String userName) {
		// TODO Auto-generated method stub
		return inde.count(userName);
	}

	@Override
	public void delete(String id, String name, String money) {
		// TODO Auto-generated method stub
		this.inde.delete(id,name,money);
	}

	@Override
	public void cancel(String id) {
		// TODO Auto-generated method stub
		this.inde.cancel(id);
	}

	@Override
	public List<IndentBean> main2(String name, String page, String pageSize) {
		// TODO Auto-generated method stub
		List<IndentBean> list = inde.main2(name,page,pageSize);
		return list;
	}

	@Override
	public String count2() {
		// TODO Auto-generated method stub
		return inde.count();
	}


}
