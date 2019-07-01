package com.znsd.restaurant.servers.impl.order;

import com.znsd.restaurant.bean.order.RecordBean;
import com.znsd.restaurant.dao.order.RecordDao;
import com.znsd.restaurant.dao.impl.order.RecordDaoImpl;
import com.znsd.restaurant.servers.order.RecordServers;

import java.util.List;

public class RecordServersImpl implements RecordServers {
	
	private RecordDao recordDao = new RecordDaoImpl();

	public List<RecordBean> getOrders(RecordBean record){
		return recordDao.getOrders(record);
	}

	@Override
	public Boolean deleteOrder(int id) {
		return recordDao.deleteOrder(id);
	}
	
	public List<RecordBean> consumptionQuery(int start,int pageSize){
		return recordDao.consumptionQuery(start, pageSize);
	}
	
	public void delete(String data){
		recordDao.Deskdelete(data);
	}
	
	public List<RecordBean> likeQuery(int start,int pageSize,String name){
		return recordDao.likeQuery(start, pageSize, name);
	}
	
	public int getCount(){
		return recordDao.getCount();
	}
	
	public int likeCount(String name){
		return recordDao.likeCount(name);
	}

	public void Deskdelete(String record) {
		recordDao.Deskdelete(record);
	}

	@Override
	public List<RecordBean> main2(String name, String page, String pageSize) {
		// TODO Auto-generated method stub
		List<RecordBean> list = recordDao.main2(name,page,pageSize);
		return list;
	}

	@Override
	public String count2() {
		// TODO Auto-generated method stub
		return recordDao.count();
	}

	@Override
	public String count(String userName) {
		// TODO Auto-generated method stub
		return recordDao.count(userName);
	}

}
