package com.sc.auth.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		if(articleType != 0){
			return showArticlesByType(request, response, actionForward);
		}else{
			return showAllArticle(request, response, actionForward);
		}
	}
	
	private String showAllArticle(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {
		try {
			List<ArticleVo> articles = articleManageService.queryArticles();
			request.setAttribute("articles", articles);
			return actionForward.findForward("showAll");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String showArticlesByType(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {
		try {
			int articleType = ParamUtils.getInt(request, "t", 0); 
			List<ArticleVo> articles = articleManageService.queryArticlesByType(articleType);
			request.setAttribute("articles", articles);
			return actionForward.findForward("showAll");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
