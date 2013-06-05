package com.sc.auth.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.sc.auth.core.Action;
import com.sc.auth.core.ActionForward;
import com.sc.auth.core.Env;
import com.sc.auth.service.ArticleManageService;
import com.sc.auth.util.ParamUtils;
import com.sc.auth.vo.ArticleVo;

public class BlogIndexAction extends Action{

	private ArticleManageService articleManageService = Env.getBean("articleManageService");	
	
	@Override
	public String excute(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward)
			throws IOException {
		int articleType = ParamUtils.getInt(request, "t", 0);
		String searchValue = ParamUtils.getString(request, "s", "");
		if(StringUtils.isNotBlank(searchValue)){
			return searchArticle(request, response, actionForward);
		}else if(articleType != 0){
			return showArticlesByType(request, response, actionForward);
		}else{
			return showAllArticle(request, response, actionForward);
		}
	}
	
	private String searchArticle(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {
		try {
			String searchValue = ParamUtils.getString(request, "s", "");
			List<ArticleVo> articles = articleManageService.searchArticles(searchValue);
			request.setAttribute("articles", articles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actionForward.findForward("showAll");
	}

	private String showAllArticle(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {
		try {
			List<ArticleVo> articles = articleManageService.queryArticles();
			request.setAttribute("articles", articles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actionForward.findForward("showAll");
	}

	private String showArticlesByType(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {
		try {
			int articleType = ParamUtils.getInt(request, "t", 0); 
			List<ArticleVo> articles = articleManageService.queryArticlesByType(articleType);
			request.setAttribute("articles", articles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actionForward.findForward("showAll");
	}
	
	
	
}
