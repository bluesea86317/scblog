package com.sc.auth.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;

import com.sc.auth.exception.DataSourceInitException;
import com.sc.auth.util.ParamUtils;

public class DaoSupport {
//	private BasicDataSource dataSource;
	
	public boolean insert(){
		boolean flag = true;
		return flag;
	}
	
	public boolean update(){
		boolean flag = true;
		return flag;
	}
	
	public List<?> queryForList(String sqlMapConfig, Object param, Class clz) throws SQLException{
		List<?> result = new ArrayList<Object>();
		ResultSet rs;
		try {
			rs = getDataSource().getConnection().createStatement().executeQuery("");
			rs.getString("");
			return result;
		} catch (DataSourceInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Object queryForObject(String sqlMapConfig, Map<String,Object> param, Class<?> clazz) throws SQLException{
		Object object = null;
		try {
			String sql = ParamUtils.getSql(sqlMapConfig, param);
			ResultSet rs = getDataSource().getConnection().createStatement().executeQuery(sql);
			Field[] fields = clazz.getDeclaredFields();
			if(rs.next()){
				object = clazz.newInstance();
				for(Field field : fields){
					Method method = clazz.getMethod("set" + ParamUtils.initMethodName(field.getName()), field.getType());
					method.invoke(object, rs.getObject(field.getName()));
				}				
			}
			return object;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataSourceInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean excuteSql(String sql){
		boolean flag = true;
		try {
			getDataSource().getConnection().createStatement().execute(sql);			
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} catch (DataSourceInitException e) {
			e.printStackTrace();
			flag = false;
		}
		
		return flag;
	}
	
	public boolean delete(){
		boolean flag = true;
		return flag;
	}

	public BasicDataSource getDataSource() throws DataSourceInitException {
		return DataSourceFactory.getDataSource();
	}
	
	
}
