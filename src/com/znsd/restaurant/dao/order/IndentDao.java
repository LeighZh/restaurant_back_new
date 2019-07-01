package com.znsd.restaurant.dao.order;

import com.znsd.restaurant.bean.order.IndentBean;

import java.util.List;

/**
**oyb
**2018年7月27日
**/
public interface IndentDao {

	List<IndentBean> getTheOrder(int id);

	String count(String userName);

	void delete(String id, String name, String money);

	void cancel(String id);

	List<IndentBean> main2(String name, String page, String pageSize);

	String count();

}
