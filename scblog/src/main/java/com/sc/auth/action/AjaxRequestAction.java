package com.sc.auth.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.sc.auth.action.service.ArticleManageService;
import com.sc.auth.action.service.ArticleTypeService;
import com.sc.auth.core.Action;
import com.sc.auth.core.ActionForward;
import com.sc.auth.util.ParamUtils;
import com.sc.auth.vo.ArticleType;
import com.sc.auth.vo.ArticleVo;

public class AjaxRequestAction extends Action {

	private ArticleManageService articleManageService = ArticleManageService.getInstance();
	private ArticleTypeService articleTypeService = ArticleTypeService.getInstance();
	
	@Override
	public String excute(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward)
			throws IOException {
		String action = ParamUtils.getString(request, "action", "");
		if("listRecentArticles".equals(action)){
			return listRecentArticles(request, response, actionForward);
		}else if("listArticleTypes".equals(action)){
			return listArticleTypes(request, response, actionForward);
		}
		return null;
	}

	private String listArticleTypes(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {
		try {
			List<ArticleType> articleTypes = articleTypeService.queryArtilcType();
			JSONArray jsonArray = new JSONArray();
			jsonArray.addAll(articleTypes);
			outPut(response, jsonArray.toString());
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return null;
	}

	private String listRecentArticles(HttpServletRequest request,
			HttpServletResponse response, ActionForward forward) {
		try {
			List<ArticleVo> articles = articleManageService.queryRecentArticles();
			JSONArray jsonArray = new JSONArray();
			jsonArray.addAll(articles);
			outPut(response, jsonArray.toString());
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return null;
	}
	
}
