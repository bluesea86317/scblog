package com.sc.auth.action.dao;

import java.sql.SQLException;

import com.sc.auth.core.DaoSupport;

public class ExcSqlDao extends DaoSupport {
	
	/**
	 * 单例, 饥汉模式
	 */
	private final static ExcSqlDao excSqlDao = new ExcSqlDao();
	
	/**
	 * 直接执行一条sql
	 * @throws SQLException 
	 */
	public boolean excuteSql(String sql) throws SQLException{
		return super.excuteSql(sql);
	}
	
	public static ExcSqlDao getInstance(){
		return excSqlDao;
	}
}
