package com.znsd.restaurant.dao.impl.order;

import com.znsd.restaurant.bean.order.IndentBean;
import com.znsd.restaurant.bean.order.RecordBean;
import com.znsd.restaurant.dao.DBUtils;
import com.znsd.restaurant.dao.order.RecordDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordDaoImpl implements RecordDao{

	public List<RecordBean> getOrders(RecordBean record){
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		ResultSet query = null;
		String sql = "SELECT oid, loginName,orderTime,orderState,orderPrice FROM mainorder,USER WHERE user.id = mainorder.`userId` ";
		if(record.getRecordId() != 0){
			sql = sql + " and oid = "+ record.getRecordId();
		}
		if(record.getUserName() != null ){
			sql = sql +  " and loginName = " + record.getUserName();
		}
		if(record.getTime() != null ){
			sql = sql +  " and orderTime > " +  "'" + record.getTime()+  "'";
		}

		System.out.println(sql);

		try {
			prepare = connection.prepareStatement(sql);
			query = prepare.executeQuery();
			List<RecordBean> list = new ArrayList<RecordBean>();
			while(query.next()){
				list.add(new RecordBean(query.getInt(1),query.getString(2),query.getString(4),query.getDouble(5),query.getTimestamp(3).toString()));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteOrder(int id){
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		boolean res = false;
		try {
			prepare = connection.prepareStatement("DELETE FROM subsetorder WHERE oid = ? ");
			prepare.setInt(1, id);
			if(prepare.executeUpdate() > 0){
                prepare = connection.prepareStatement("DELETE FROM mainorder WHERE oid = ?");
                prepare.setInt(1, id);
                if(prepare.executeUpdate() > 0);{
                    res = true;
                }
            }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<RecordBean> likeQuery(int start, int pageSize, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int likeCount(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void Deskdelete(String record) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RecordBean> consumptionQuery(int start, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String select(String line, String pageSize, int first) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecordBean> main2(String name, String page, String pageSize) {
		// TODO Auto-generated method stub
		int first = (Integer.parseInt(page)-1)*Integer.parseInt(pageSize);
		Connection conn = DBUtils.getConnection();
		Statement sta = null;
		List<RecordBean> list =null ;
		try {
			sta = conn.createStatement();
			ResultSet res =sta.executeQuery("SELECT oid,loginName,orderState,orderPrice,orderTime FROM mainorder,USER WHERE mainorder.`userId` = user.id LIMIT "+first+","+pageSize);
			list = new ArrayList<RecordBean>();
			while(res.next()){
				list.add(new RecordBean(res.getInt(1),res.getString(2),res.getString(3),res.getDouble(4),res.getTimestamp(5).toString()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String count() {
		// TODO Auto-generated method stub
		Connection conn = DBUtils.getConnection();
		Statement sta = null;
		ResultSet res2=null;
		String count="";
		try {
			sta = conn.createStatement();
			res2 = sta.executeQuery("SELECT COUNT(*) FROM mainorder");
			while(res2.next()){
				count= res2.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public String count(String userName) {
		// TODO Auto-generated method stub
		Connection conn = DBUtils.getConnection();
		Statement sta = null;
		ResultSet res2=null;
		String count="";
//		try {
//			sta = conn.createStatement();
//			res2 = sta.executeQuery("SELECT COUNT(*) FROM indent where userName="+userName+"");
//			while(res2.next()){
//				count= res2.getString(1);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return count;
	}

}
