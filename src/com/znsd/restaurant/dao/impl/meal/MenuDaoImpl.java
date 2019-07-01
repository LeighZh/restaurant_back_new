package com.znsd.restaurant.dao.impl.meal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.znsd.restaurant.bean.meal.MenuBean;
import com.znsd.restaurant.dao.DBUtils;
import com.znsd.restaurant.dao.meal.MenuDao;

public class MenuDaoImpl extends DBUtils implements MenuDao {

	@Override
	public Boolean insert(MenuBean ben) {
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		boolean res = false;

		try {
			prepare = connection.prepareStatement("insert into  meals (mealSeriesId,mealName,mealSummarize,mealDescription,mealPrice,mealImage) values(?,?,?,?,?,?)");
			prepare.setInt(1, ben.getMealSeriesId());
			prepare.setString(2, ben.getMealName());
			prepare.setString(3, ben.getMealSummarize());
			prepare.setString(4, ben.getMealDescription());
			prepare.setString(4, ben.getMealDescription());
			prepare.setString(4, ben.getMealDescription());
			prepare.setDouble(5,ben.getMealPrice());
			prepare.setString(6,ben.getMealImage());
			prepare.execute();
			res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Boolean update(MenuBean ben) {
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		boolean res = false;
		try {
			prepare = connection.prepareStatement(" update  meals set mealSeriesId = ?, mealName = ? ,mealSummarize = ?,mealDescription = ?,mealPrice = ?,mealImage = ?");
			prepare.setInt(1, ben.getMealSeriesId());
			prepare.setString(2, ben.getMealName());
			prepare.setString(3, ben.getMealSummarize());
			prepare.setString(4, ben.getMealDescription());
			prepare.setString(4, ben.getMealDescription());
			prepare.setString(4, ben.getMealDescription());
			prepare.setDouble(5,ben.getMealPrice());
			prepare.setString(6,ben.getMealImage());
			prepare.execute();

			prepare.execute();
			res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Boolean delete(int id) {
		// TODO Auto-generated method stub
		Connection con=DBUtils.getConnection();
		String 	sql="delete from meals where mealId  = ?";
		PreparedStatement pre=null;
		Boolean res = false;
		try {
			pre=con.prepareStatement(sql);
			pre.setInt(1,id);
			pre.execute();
			res = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = false;
		}
		return res;
	}
	@Override
	public List<MenuBean> query(MenuBean menu){
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		ResultSet query = null;
		String sql = "SELECT mealId,mealName,mealPrice,seriesId,seriesName,mealImage,mealDescription,mealSummarize FROM meals,mealseries WHERE meals.mealSeriesId = mealseries.seriesId ";

		if(menu.getMealId() != 0){
			sql = sql + " and mealId= "+ menu.getMealImage();
		}
		if(menu.getMealName() != null ){
			sql = sql +  " and mealName = " +  "'" + menu.getMealName()+  "'";
		}
		if(menu.getMealSeriesId() != 0 ){
			sql = sql +  " and seriesId = '" + menu.getMealSeriesId() + "'";
		}
		try {
			prepare = connection.prepareStatement(sql);
			query = prepare.executeQuery();
			List<MenuBean> list = new ArrayList<MenuBean>();
			while(query.next()){
				//int mealId, String mealName, double mealPrice, int mealSeriesId, String seriesName, String mealImage, String mealDescription, String mealSummarize
				list.add(new MenuBean(query.getInt(1),query.getString(2),query.getDouble(3),query.getInt(4),query.getString(5),query.getString(6),query.getString(7),query.getString(8)));
			}
			System.out.println(list.toString());
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
