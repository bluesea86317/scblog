package com.sc.auth.core;

import org.apache.commons.dbcp.BasicDataSource;

import com.sc.auth.exception.DataSourceInitException;

public class DataSourceFactory {
//	static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	static final String URL = "jdbc:sqlserver://localhost;databasename=books;user=sa;password=softeem1";

	private static BasicDataSource ds = null;
	public static BasicDataSource getDataSource() throws DataSourceInitException{
		if( ds == null){
			throw new DataSourceInitException("Database connection initialization exception !"); 
		}
		return ds;
	}
	
	public static void init(BasicDataSource dataSource){
		if( ds == null){
			ds = dataSource;
		}
	}
}
