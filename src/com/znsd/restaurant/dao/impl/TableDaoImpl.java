package com.znsd.restaurant.dao.impl;

import com.znsd.restaurant.bean.TableZibean;
import com.znsd.restaurant.dao.DBUtils;
import com.znsd.restaurant.dao.TableDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TableDaoImpl implements TableDao {

	@Override
	public List<TableZibean> main(String page, String pageSize) {
		// TODO Auto-generated method stub
		System.out.println("main");
		int first = (Integer.parseInt(page)-1)*Integer.parseInt(pageSize);
		Connection conn = DBUtils.getConnection();
		Statement sta = null;
		List<TableZibean> list =null ;
		try {
			sta = conn.createStatement();
			ResultSet res =sta.executeQuery("SELECT * FROM tableZi WHERE SIGN = '正常' LIMIT "+first+","+pageSize);
			list = new ArrayList<TableZibean>();
			while(res.next()){
				list.add(new TableZibean(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7)));
			}
			ResultSet res2 =sta.executeQuery("SELECT COUNT(*) FROM tableZi");
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
		System.out.println("count");
		ResultSet res2=null;
		String count="";
		try {
			sta = conn.createStatement();
			res2 = sta.executeQuery("SELECT COUNT(*) FROM tableZi WHERE SIGN = '正常'");
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
	public void add(String id, String userName) {
		// TODO Auto-generated method stub
		System.out.println("add");
		Connection conn = DBUtils.getConnection();
		Statement sta = null;
		try {
			sta = conn.createStatement();
			//res = sta.executeQuery("SELECT tableStatus FROM tableZi WHERE tableId="+id);
			sta = conn.createStatement();
			System.out.println("UPDATE tableZi SET tableStatus='已使用',userName"+userName+" WHERE tableId="+id);
			sta.execute("UPDATE tableZi SET tableStatus='已使用',userName="+userName+" WHERE tableId="+id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String id, String userName) {
		// TODO Auto-generated method stub
		System.out.println("delete");
		Connection conn = DBUtils.getConnection();
		Statement sta = null;
		try {
			sta = conn.createStatement();
			//res = sta.executeQuery("SELECT tableStatus FROM tableZi WHERE tableId="+id);
			sta = conn.createStatement();
			System.out.println("UPDATE tableZi SET tableStatus='已使用',userName"+userName+" WHERE tableId="+id);
			sta.execute("UPDATE tableZi SET tableStatus='空闲',userName=0 WHERE tableId="+id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public List<TableZibean> main2(String page, String pageSize) {
		// TODO Auto-generated method stub
		int first = (Integer.parseInt(page)-1)*Integer.parseInt(pageSize);
		Connection conn = DBUtils.getConnection();
		Statement sta = null;
		List<TableZibean> list =null ;
		try {
			sta = conn.createStatement();
			ResultSet res =sta.executeQuery("SELECT * FROM tableZi LIMIT "+first+","+pageSize);
			list = new ArrayList<TableZibean>();
			while(res.next()){
				list.add(new TableZibean(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7)));
			}
			ResultSet res2 =sta.executeQuery("SELECT COUNT(*) FROM tableZi");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void remove(String id, String userName) {
		// TODO Auto-generated method stub
		Connection conn = DBUtils.getConnection();
		Statement sta =null;
		try {
			sta=conn.createStatement();
			sta.execute("UPDATE tablezi SET SIGN='已删除' WHERE tableId="+id);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("异常");
		}
	}

	@Override
	public void board(String name,String id) {
		// TODO Auto-generated method stub
		Connection conn = DBUtils.getConnection();
		Statement sta =null;
		try {
			sta = conn.createStatement();
			sta.execute("UPDATE tablezi SET tableName='"+name+"' WHERE tableId="+id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void increase(String name, String line, String row) {
		// TODO Auto-generated method stub
		Connection conn = DBUtils.getConnection();
		Statement sta = null;
		try {
			sta = conn.createStatement();
			System.out.println("INSERT INTO tablezi(tableName,tableStatus,line,ROW,createTime,SIGN) VALUE('"+name+"','空闲',"+line+","+row+",NOW(),'正常')");
			sta.execute("INSERT INTO tablezi(tableName,tableStatus,line,ROW,createTime,SIGN) VALUE('"+name+"','空闲',"+line+","+row+",NOW(),'正常')");
 		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	

}
