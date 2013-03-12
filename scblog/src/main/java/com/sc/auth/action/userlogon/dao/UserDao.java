package com.sc.auth.action.userlogon.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sc.auth.action.userlogon.BaseUser;
import com.sc.auth.core.DaoSupport;
import com.sc.auth.exception.DataSourceInitException;

public class UserDao extends DaoSupport {
	
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
		BaseUser baseUser;
		paramMap.put("userName", userName);
		paramMap.put("password", password);
		baseUser = (BaseUser)queryForObject("select * from t_user where userName = #userName# and password = PASSWORD(#password#)", paramMap, BaseUser.class);
		return baseUser;
	}
	
	public boolean excuteSql(String sql){
		return super.excuteSql(sql);
	}
}
