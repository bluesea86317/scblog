package com.sc.auth.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ibm.icu.text.SimpleDateFormat;

public class DBUtils {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 获取结果集中所以的字段名称
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static List<String> getColumnNamesByRS(ResultSet rs) throws SQLException{
		List<String> columnNameList = new ArrayList<String>();
		ResultSetMetaData rss = rs.getMetaData();		
		int columnCount = rss.getColumnCount();		
		for(int i = 1; i<= columnCount; i++){
			columnNameList.add(rss.getColumnName(i));
		}
		return columnNameList;
	}
	
	@SuppressWarnings("unchecked")
	public static String getSql(String sql, Object param){
		try {
			if(param.getClass().getName().indexOf("Map") != -1){
				Map<String,Object> paramMap = (HashMap<String,Object>)param;
				Iterator<?> itr = paramMap.keySet().iterator();
				while(itr.hasNext()){
					String key = (String)itr.next();
					Object value = paramMap.get(key);
					if(sql.indexOf("#"+ key +"#") != -1){
						if(null == value){
							sql = sql.replace("#" + key + "#",String.valueOf(value));
						}else{
							String clazName = value.getClass().getName();
							if(Date.class.getName().equals(clazName)){
								sql = sql.replaceAll("#"+ key +"#", "'" + sdf.format(value) + "'");
							}else if(String.class.getName().equals(clazName)){
								sql = sql.replace("#" + key + "#","'" + String.valueOf(value) + "'");
							}else{
								sql = sql.replace("#" + key + "#",String.valueOf(value));
							}
						}
					}
				}
			}else{				
				Method[] methods = param.getClass().getMethods();				
				for(Method method : methods){
					if(method.getName().indexOf("get") != -1){
						Object value = method.invoke(param);
						String property = ParamUtils.lowerCaseMethodName(method.getName().substring(3));
						if(sql.indexOf("#"+ property +"#") != -1){							
							if(null == value){
								sql = sql.replace("#" + property + "#",String.valueOf(value));
							}else{
								String clazName = value.getClass().getName();
								if(Date.class.getName().equals(clazName)){
									sql = sql.replaceAll("#"+ property +"#", "'" + sdf.format(value) + "'");
								}else if(String.class.getName().equals(clazName)){
									sql = sql.replace("#" + property + "#","'" + String.valueOf(value) + "'");
								}else{
									sql = sql.replace("#" + property + "#",String.valueOf(value));
								}
							}
						}
					}
				}	
			}
			System.out.println("The sql is : " + sql);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sql;
	}
}
