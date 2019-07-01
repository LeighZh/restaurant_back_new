package com.znsd.restaurant.dao.impl;

import com.znsd.restaurant.bean.VegetableTypeBean;
import com.znsd.restaurant.dao.CookingDao;
import com.znsd.restaurant.dao.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CookingDaoImpl implements CookingDao{
	@Override
	public String add(String name) {
		Connection con=DBUtils.getConnection();
		String sql="insert into vegetabletype(vegetableName,addTime,sign) values(?,Now(),'正常')";
		PreparedStatement pre=null;
		try {
			pre=con.prepareStatement(sql);
			pre.setString(1,name);
			pre.execute();
			return "";
		} catch (SQLException e) {
			System.out.println("6666666");
			return "菜系名称重复";
		}
		
	}

	@Override
	public void delete(String name) {
		// TODO Auto-generated method stub
		Connection con=DBUtils.getConnection();
		String 	sql="update vegetabletype set sign='删除' where vegetableid in(?)";
		PreparedStatement pre=null;
		try {
			pre=con.prepareStatement(sql);
			pre.setString(1,name);
			pre.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<VegetableTypeBean> query() {
		// TODO Auto-generated method stub
		Connection con=DBUtils.getConnection();
		String sql="select vegetableId,vegetableName from vegetabletype where sign='正常'";
		PreparedStatement pre=null;
		List<VegetableTypeBean> list=new ArrayList<VegetableTypeBean>();
//		try {
//			pre=con.prepareStatement(sql);
//			ResultSet res=pre.executeQuery();
//			while(res.next()){
//				list.add(new VegetableTypeBean(res.getInt(1),res.getString(2)));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("asd");
//		}
		return list;
	}

	@Override
	public void mod(String name) {
		// TODO Auto-generated method stub
		
	}
		
}
