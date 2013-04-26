package com.sc.auth.core;

import java.sql.SQLException;
import java.util.List;

public interface DaoTemplate {

	public List queryForList(String sqlMapConfig, Object param) throws SQLException;	
	
	public List queryForList(String sqlMapConfig) throws SQLException;

	public int insert(String sqlMapConfig, Object param) throws SQLException;
	
	public int update(String sqlMapConfig, Object param) throws SQLException;
	
	public Object queryForObject(String sqlMapConfig, Object param) throws SQLException;
	
	public Object queryForObject(String sqlMapConfig) throws SQLException;
	
	public int delete(String sqlMapConfig, Object param) throws SQLException;
}
