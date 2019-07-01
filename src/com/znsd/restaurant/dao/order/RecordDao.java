package com.znsd.restaurant.dao.order;

import com.znsd.restaurant.bean.order.IndentBean;
import com.znsd.restaurant.bean.order.RecordBean;

import java.util.List;

public interface RecordDao {

	public List<RecordBean> getOrders(RecordBean record);

	public  boolean deleteOrder(int id);

	List<RecordBean> likeQuery(int start, int pageSize, String name);

	int getCount();

	int likeCount(String name);

	void Deskdelete(String record);

	List<RecordBean> consumptionQuery(int start, int pageSize);

	String select(String line, String pageSize, int first);

	List<RecordBean> main2(String name, String page, String pageSize);

	String count();

	String count(String userName);

}
