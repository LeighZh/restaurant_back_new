package com.znsd.restaurant.dao.impl.System;

import com.znsd.restaurant.bean.order.RecordBean;
import com.znsd.restaurant.bean.system.UserBean;
import com.znsd.restaurant.dao.DBUtils;
import com.znsd.restaurant.dao.System.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean userLogin(String name,String password) {
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		PreparedStatement prepareTwo = null;
		ResultSet query = null;
		try {
			prepare = connection.prepareStatement("select userName from user where userName=? and password=? and sign='正常'");
			prepare.setString(1, name);
			prepare.setString(2, password);
			query = prepare.executeQuery();
			if(query.next()){
				prepareTwo = connection.prepareStatement("UPDATE USER SET lastTime = NOW() WHERE userName = ? and sign='正常'");
				prepareTwo.setString(1, name);
				prepareTwo.execute();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(query!=null)
					query.close();
				if(prepare!=null)
					prepare.close();
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public void resetPassword(String name,String password){
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		try {
			prepare = connection.prepareStatement("UPDATE admin SET loginPwd = ? WHERE loginName=?");
			prepare.setString(2, name);
			prepare.setString(1, password);
			prepare.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    public void resetUserPassword(String name,String password){
        Connection connection = DBUtils.getConnection();
        PreparedStatement prepare = null;
        try {
            prepare = connection.prepareStatement("UPDATE user SET loginPwd = ? WHERE id=?");
            prepare.setString(2, name);
            prepare.setString(1, password);
            prepare.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean updata(UserBean user) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement prepare = null;
        boolean res = false;
        try {
            prepare = connection.prepareStatement("UPDATE user SET loginName = ?,trueName = ?,phone = ?,email = ?,address = ?  WHERE id= ?");
            prepare.setString(1, user.getUserName());
            prepare.setString(2, user.getTrueName());
            prepare.setString(3, user.getPhone());
            prepare.setString(4, user.getEmail());
            prepare.setString(5, user.getAddress());
            prepare.setInt(6, user.getUserId());

            prepare.execute();
            res = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

	@Override
	public boolean updateAdmin(UserBean user) {
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		boolean res = false;
		try {
			prepare = connection.prepareStatement("UPDATE admin SET loginName = ?,role = ?  WHERE id= ?");
			prepare.setString(1, user.getUserName());
			prepare.setString(2, user.getRole());
			prepare.setInt(3, user.getUserId());

			prepare.execute();
			res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}


	public boolean delete(int id){
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		boolean res = false;
		try {
			prepare = connection.prepareStatement("delete from user where id = ?");
			prepare.setInt(1, id);
			if(prepare.executeUpdate() > 0);
			res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

    @Override
    public boolean insert(UserBean user) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement prepare = null;
        boolean res = false;
        try {
            prepare = connection.prepareStatement("insert into  user (loginName,trueName,phone,email,address) values(?,?,?,?,?)");
            prepare.setString(1, user.getUserName());
            prepare.setString(2, user.getTrueName());
            prepare.setString(3, user.getPhone());
            prepare.setString(4, user.getEmail());
            prepare.setString(5, user.getAddress());

            prepare.execute();
            res = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

	@Override
	public boolean insertAdmin(UserBean user) {
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		boolean res = false;
		try {
			prepare = connection.prepareStatement("insert into  admin (loginName,role) values(?,?)");
			prepare.setString(1, user.getUserName());
			prepare.setString(2, user.getRole());
			prepare.execute();
			res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public UserBean afterRegister(String name,String password){
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		ResultSet query = null;
		try {
			prepare = connection.prepareStatement("select id,loginName from admin where loginName=? and loginPwd=? !=0");
			prepare.setString(1, name);
			prepare.setString(2, password);
			query = prepare.executeQuery();
			if(query.next()){
				UserBean user = new UserBean(query.getInt(1),query.getString(2));
				System.out.println(name + "登陆成功");
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<UserBean> getUserByLoginName(String name){
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		ResultSet query = null;
		try {
			prepare = connection.prepareStatement("SELECT id,loginName,createTime,trueName,phone,email,address,SUM(orderPrice) money \n" +
					"FROM USER,mainorder WHERE mainorder.userId = user.id and loginName = ? GROUP BY user.id ORDER BY money DESC");
			prepare.setString(1, name);
			query = prepare.executeQuery();
			List<UserBean> list = new ArrayList<UserBean>();
			while(query.next()){
                list.add(new UserBean(query.getInt(1),query.getString(2),query.getTimestamp(3).toString(),query.getString(4),query.getString(5),query.getString(6),query.getString(7),query.getDouble(8)));
            }
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getRecordCount(String name){
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		ResultSet query = null;
		try {
			prepare = connection.prepareStatement("SELECT count(1) FROM record WHERE userName = ? AND SIGN = '正常';");
			prepare.setString(1, name);
			query = prepare.executeQuery();
			while(query.next()){
				return query.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(query!=null)
					query.close();
				if(prepare!=null)
					prepare.close();
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public void recordRemove(String record){
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		try {
			prepare = connection.prepareStatement("UPDATE record SET SIGN = '用户删除'  WHERE recordId IN ("+record+")");
			prepare.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(prepare!=null)
					prepare.close();
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<UserBean> afterQuery(UserBean user){
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		ResultSet query = null;
		String sql = "SELECT id,loginName,createTime,trueName,phone,email,address,SUM(orderPrice) money \n" +
				"FROM USER left join mainorder on mainorder.userId = user.id WHERE user.money >= 0 ";
		if(user.getUserId() != 0){
		    sql = sql + " and id = "+ user.getUserId();
		}
		if(user.getUserName() != null ){
		    sql = sql +  " and loginName = " +  "'" + user.getUserName()+  "'";
        }
        if(user.getTrueName() != null ){
            sql = sql +  " and trueName = '" + user.getTrueName() + "'";
        }
        if(user.getPhone() != null ){
            sql = sql +  " and phone = " +  "'" + user.getPhone()+  "'";
        }
        if(user.getEmail() != null ){
            sql = sql +  " and email = " +  "'" + user.getEmail()+  "'";
        }
        if(user.getAddress() != null ){
            sql = sql +  " and address = " +  "'" + user.getAddress()+  "'";
        }
        if(user.getCreateTime() != null ){
            sql = sql +  " and createTime > " +  "'" + user.getCreateTime()+  "'";
        }
        if(user.getMoney() != -1 ){
            sql = sql +  " and money <= " + user.getMoney();
        }

        sql += " GROUP BY user.id ORDER BY money DESC";

        System.out.println(sql);

		try {
			prepare = connection.prepareStatement(sql);
			query = prepare.executeQuery();
			List<UserBean> list = new ArrayList<UserBean>();
			while(query.next()){
				list.add(new UserBean(query.getInt(1),query.getString(2),query.getTimestamp(3).toString(),query.getString(4),query.getString(5),query.getString(6),query.getString(7),query.getDouble(8)));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<UserBean> likeAfterQuery(String likeName){
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		ResultSet query = null;
		try {
			prepare = connection.prepareStatement("SELECT userName,createTime,lastTime,number,(SELECT SUM(consumptionMoney) FROM record WHERE userName=a.userName),sign FROM USER a where a.userName like ?");
			prepare.setString(1, likeName+"%");
			query = prepare.executeQuery();
			List<UserBean> list = new ArrayList<UserBean>();
			while(query.next()){
				//list.add(new UserBean(query.getString(1),query.getTimestamp(2).toString(),query.getString(3),query.getString(4),query.getDouble(5),query.getString(6)));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(query!=null)
					query.close();
				if(prepare!=null)
					prepare.close();
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public int afterUserCount(){
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		ResultSet query = null;
		try {
			prepare = connection.prepareStatement("select count(1) from user");
			query = prepare.executeQuery();  
			while(query.next()){
				return query.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(query!=null)
					query.close();
				if(prepare!=null)
					prepare.close();
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public int likeAfterCount(String name){
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		ResultSet query = null;
		try {
			prepare = connection.prepareStatement("select count(1) from user where loginName = ?");
			prepare.setString(1, name);
			query = prepare.executeQuery();  
			while(query.next()){
				return query.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(query!=null)
					query.close();
				if(prepare!=null)
					prepare.close();
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

    @Override
    public List<UserBean> getAdmins(UserBean user) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement prepare = null;
        ResultSet query = null;
        String sql = "SELECT id,loginName,role FROM admin where id >= 0 ";
        if(user.getUserId() != 0){
            sql = sql + " and id = "+ user.getUserId();
        }
        if(user.getUserName() != null ){
            sql = sql +  " and loginName = "+  "'" + user.getUserName()+  "'";
        }
        if(user.getRole() != null ){
            sql = sql +  " and role = " +  "'" + user.getRole()+  "'";
        }
        System.out.println(sql);

        try {
            prepare = connection.prepareStatement(sql);
            query = prepare.executeQuery();
            List<UserBean> list = new ArrayList<UserBean>();
            while(query.next()){
                list.add(new UserBean(query.getInt(1),query.getString(2),query.getString(3)));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

	@Override
	public Boolean resetAdminPassword(int id, String password) {
		Connection connection = DBUtils.getConnection();
		PreparedStatement prepare = null;
		Boolean res = false;
		try {
			prepare = connection.prepareStatement("UPDATE admin SET loginPwd = ? WHERE id=?");
			prepare.setString(1, password);
			prepare.setInt(2,id);
			prepare.execute();
			res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
