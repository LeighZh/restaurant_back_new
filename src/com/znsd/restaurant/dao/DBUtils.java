package com.znsd.restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//工具类
public class DBUtils {
	//获取connection连接
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://10.203.1.66:3306/restaurant?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai","root","123456");
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void executeS(String sql,Object ...obj){  
		try {
		PreparedStatement ps=getConnection().prepareStatement(sql);
			for(int i=0;i<obj.length;i++){
				ps.setObject(i+1,obj[i]);
			}
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}
