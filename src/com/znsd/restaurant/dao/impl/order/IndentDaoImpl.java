package com.znsd.restaurant.dao.impl.order;

import com.znsd.restaurant.bean.order.IndentBean;
import com.znsd.restaurant.bean.order.RecordBean;
import com.znsd.restaurant.dao.DBUtils;
import com.znsd.restaurant.dao.order.IndentDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
**oyb
**2018年7月27日
**/
public class IndentDaoImpl implements IndentDao {

	@Override
	public List<IndentBean> getTheOrder(int id) {
		// TODO Auto-generated method stub
		Connection conn = DBUtils.getConnection();
		PreparedStatement prepare = null;
		List<IndentBean> list =null ;
		ResultSet res = null;
		try {
			prepare = conn.prepareStatement("SELECT s.odId subOrderId,m.mealName,m.`mealImage`,m.`mealPrice`,s.mealCount,s.mealPrice sumPrice FROM subsetorder s,meals m WHERE odId = ? AND m.mealId =s.`mealId`");
			prepare.setInt(1, id);
			res = prepare.executeQuery();
			list = new ArrayList<IndentBean>();
			while(res.next()){
				list.add(new IndentBean(res.getInt(1),res.getString(2),res.getString(3),res.getDouble(4),res.getInt(5),res.getDouble(6)));
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
//			res2 = sta.executeQuery("SELECT COUNT(*) FROM indent where userName="+userName+" AND SIGN = '正常'");
//			while(res2.next()){
//				count= res2.getString(1);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return count;
	}

	@Override
	public void delete(String id, String name, String money) {
		// TODO Auto-generated method stub
		Connection conn = DBUtils.getConnection();
		Statement sta = null;
		try {
			sta = conn.createStatement();
			System.out.println("INSERT INTO record(userName,consumptionTime,consumptionMoney) VALUE("+name+",NOW(),"+money+");");
			sta.execute("UPDATE indent SET STATUS='已结账'  WHERE indentId="+id);
			sta.execute("INSERT INTO record(userName,consumptionTime,consumptionMoney) VALUE("+name+",NOW(),"+money+");");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void cancel(String id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Connection conn = DBUtils.getConnection();
		Statement sta = null;
		try {
			sta = conn.createStatement();
			sta.execute("UPDATE indent SET STATUS='已取消'  WHERE indentId="+id);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public List<IndentBean> main2(String name, String page, String pageSize) {
		// TODO Auto-generated method stub
		int first = (Integer.parseInt(page)-1)*Integer.parseInt(pageSize);
		Connection conn = DBUtils.getConnection();
		Statement sta = null;
		List<IndentBean> list =null ;
		try {
			sta = conn.createStatement();
			ResultSet res =sta.executeQuery("SELECT * FROM mainorder LIMIT "+first+","+pageSize);
			list = new ArrayList<IndentBean>();
			while(res.next()){
				//list.add(new RecordBean(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getDouble(7)));
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

}
