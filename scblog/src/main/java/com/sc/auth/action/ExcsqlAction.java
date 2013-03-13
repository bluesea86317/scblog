package com.sc.auth.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sc.auth.action.dao.ExcSqlDao;
import com.sc.auth.core.Action;
import com.sc.auth.util.ParamUtils;

public class ExcsqlAction extends Action {

	private ExcSqlDao excSqlDao = ExcSqlDao.getInstance();
	@Override
	public String excute(HttpServletRequest request,
			HttpServletResponse response) throws IOException {		
		return excuteSql(request,response);
	}
	
	private String excuteSql(HttpServletRequest request,
			HttpServletResponse response) {
		String sql = ParamUtils.getString(request, "sql", "");
		
		try {
			if(!excSqlDao.excuteSql(sql)){
				return "failure";
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return "success";
	}
	
}
