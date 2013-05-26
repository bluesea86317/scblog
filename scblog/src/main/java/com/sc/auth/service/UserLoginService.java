package com.sc.auth.service;

import java.sql.SQLException;

import com.sc.auth.action.dao.UserDao;
import com.sc.auth.vo.BaseUser;

public class UserLoginService {

	private UserDao userDao;
	
	public BaseUser getUser(String userName, String password) throws SQLException{
		return getUserDao().getUser(userName, password);
	}
	
	public BaseUser getBaseUser(String userName, String password) throws SQLException{
		return new BaseUser();
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
