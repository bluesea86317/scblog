package com.sc.auth.core;


import javax.sql.DataSource;

import com.sc.auth.exception.DataSourceInitException;

public class DataSourceFactory {

	private static DataSource ds = null;
//	单例模式获取数据库连接
	public static DataSource getDataSource() throws DataSourceInitException{
		if( ds == null){
			throw new DataSourceInitException("Database connection initialization exception !"); 
		}
		return ds;
	}
	
	public static void init(DataSource dataSource){
		if( ds == null){
			ds = dataSource;
		}
	}
}
