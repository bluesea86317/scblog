package com.sc.auth.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.sc.auth.action.service.ExcSqlService;
import com.sc.auth.core.Action;
import com.sc.auth.core.ActionForward;
import com.sc.auth.core.transaction.Env;
import com.sc.auth.vo.ArticleVo;

public class ExcsqlAction extends Action {

	private ExcSqlService excSqlService = Env.getBean("com.sc.auth.action.service.ExcSqlService");
	
	@Override
	public String excute(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) throws IOException {		
		return excuteSql(request,response);
	}
	
	private String excuteSql(HttpServletRequest request,
			HttpServletResponse response) {
		
		List<ArticleVo> vo;
		try {
			vo = excSqlService.queryArticles();
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
