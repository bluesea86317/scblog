package com.sc.auth.core;


import javax.sql.DataSource;

import com.sc.auth.exception.DataSourceInitException;

public class DataSourceFactory {
//	static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	static final String URL = "jdbc:sqlserver://localhost;databasename=books;user=sa;password=softeem1";

	private static DataSource ds = null;
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
