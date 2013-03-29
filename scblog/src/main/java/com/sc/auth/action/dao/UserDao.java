package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sc.auth.core.DaoSupport;
import com.sc.auth.exception.DataSourceInitException;
import com.sc.auth.vo.BaseUser;

public class UserDao extends DaoSupport {
	
	public static UserDao getInstance(){
		return new UserDao();
	}
	
	/**
	 * 查询用户，用来验证用户名，密码是否正确
	 * @param userName
	 * @param password
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws DataSourceInitException 
	 * @throws SQLException 
	 */
	public BaseUser getUser(String userName, String password) throws SQLException{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String sql = "select * from t_user where userName = #userName# and password = PASSWORD(#password#)";
		BaseUser baseUser;
		paramMap.put("userName", userName);
		paramMap.put("password", password);
		baseUser = (BaseUser)queryForObject(sql, paramMap, BaseUser.class);
		return baseUser;
	}
	
}
