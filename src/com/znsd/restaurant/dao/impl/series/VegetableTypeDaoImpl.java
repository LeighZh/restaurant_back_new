package com.znsd.restaurant.dao.impl.series;

import com.znsd.restaurant.dao.DBUtils;
import com.znsd.restaurant.dao.series.VegetableTypeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VegetableTypeDaoImpl  extends DBUtils implements VegetableTypeDao {

	@Override
	public String select(String line, String pageSize, int first) {
		Connection con = getConnection();
		PreparedStatement pr =null;
		ResultSet ex = null;
		//String[] st = line.split(",");//列表每列
		String tab = "[";
		 try {
			 pr= con.prepareStatement("SELECT seriesID,seriesName FROM mealseries limit ?,?");
			 pr.setInt(1, first);
			 pr.setInt(2, Integer.parseInt(pageSize));
			 ex = pr.executeQuery();
			 while(ex.next()){
				tab+="{\"vegetableId\":\""+ex.getObject(1)+"\",\"菜系名称\":\""+ex.getObject(2)+"\"},";
			 }
			 System.out.println(tab);
			 tab = tab.substring(0,tab.length()-1);
			 tab+="]";
			 return tab;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(ex!=null)
					ex.close();
				if(pr!=null)
					pr.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public int count() {
		Connection con = getConnection();
		PreparedStatement pr =null;
		ResultSet ex = null;
		 try {
			 pr= con.prepareStatement("SELECT count(1) FROM mealseries");
			 ex = pr.executeQuery();
			 ex.next();
			 return ex.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ex!=null)
					ex.close();
				if(pr!=null)
					pr.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

}
