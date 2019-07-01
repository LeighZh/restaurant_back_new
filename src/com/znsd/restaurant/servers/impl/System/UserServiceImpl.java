package com.znsd.restaurant.servers.impl.System;

import com.znsd.restaurant.bean.order.RecordBean;
import com.znsd.restaurant.bean.system.UserBean;
import com.znsd.restaurant.dao.System.UserDao;
import com.znsd.restaurant.dao.impl.System.UserDaoImpl;
import com.znsd.restaurant.servers.System.UserService;

import java.util.List;

public class UserServiceImpl implements UserService{

	private UserDao userDao = new UserDaoImpl();
	@Override
	public boolean loginJudge(String name,String password) {
		return userDao.userLogin(name, password);
	}

    @Override
    public void resetPassword(String name, String password) {
        userDao.resetPassword(name,password);
    }

    @Override
    public void resetUserPassword(String name, String password) {
        userDao.resetUserPassword(name,password);
    }

	@Override
	public boolean updata(UserBean user) {
		return userDao.updata(user);
	}

	@Override
	public boolean updataAdmin(UserBean user) {
		return userDao.updateAdmin(user);
	}

	@Override
	public boolean delete(int id) {
		return userDao.delete(id);
	}

    @Override
    public boolean insert(UserBean user) {
        return userDao.insert(user);
    }

	@Override
	public boolean insertAdmin(UserBean user) {
		return userDao.insertAdmin(user);
	}

	@Override
	public UserBean afterRegister(String name, String password) {
		return userDao.afterRegister(name, password);
	}

	public List<UserBean>getUserByLoginName(String name){
		return userDao.getUserByLoginName(name);
	}
	
	public int getRecordCount(String name){
		return userDao.getRecordCount(name);
	}
	
	
	public void recordRemove(String record){
		userDao.recordRemove(record);
	}
	
	public List<UserBean> afterQuery(UserBean user){
		return userDao.afterQuery(user);
	}

	@Override
	public List<UserBean> getAdmins(UserBean user) {
		return userDao.getAdmins(user);
	}

	public List<UserBean> likeAfterQuery(String likeName){
		return userDao.likeAfterQuery(likeName);
	}
	
	public int afterUserCount(){
		return userDao.afterUserCount();
	}
	
	public int likeAfterCount(String name){
		return userDao.likeAfterCount(name);
	}

	@Override
	public Boolean resetAdminPassword(int id, String password) {
		return userDao.resetAdminPassword(id,password);
	}
}
