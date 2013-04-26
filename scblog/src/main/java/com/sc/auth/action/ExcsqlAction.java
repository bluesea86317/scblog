package com.sc.auth.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.sc.auth.action.dao.ExcSqlDao;
import com.sc.auth.action.dao.TestDao;
import com.sc.auth.core.Action;
import com.sc.auth.core.ActionForward;
import com.sc.auth.util.ParamUtils;
import com.sc.auth.vo.ArticleVo;

public class ExcsqlAction extends Action {

	private ExcSqlDao excSqlDao = ExcSqlDao.getInstance();
	
	@Override
	public String excute(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) throws IOException {		
		return excuteSql(request,response);
	}
	
	private String excuteSql(HttpServletRequest request,
			HttpServletResponse response) {
//		String sql = ParamUtils.getString(request, "sql", "");
//		
//		try {
//			if(!excSqlDao.excuteSql(sql)){
//				return "failure";
//			}
//		} catch (SQLException e) {			
//			e.printStackTrace();
//		}		
//		return "success";
		
		List<ArticleVo> vo;
		try {
			vo = excSqlDao.queryArticles();
			JSONArray ja = new JSONArray();
			ja.addAll(vo);
			outPut(response, ja.toString());
		} catch (SQLException e) {			
			e.printStackTrace();
			outPut(response, e.getMessage());
		}
		return null;
	}
	
}
