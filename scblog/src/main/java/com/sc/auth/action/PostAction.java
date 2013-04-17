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

public class PostAction extends Action {

	private ArticleManageService articleManageService = ArticleManageService.getInstance();
	private ArticleTypeService articleTypeService = ArticleTypeService.getInstance();
	
	@Override
	public String excute(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward)
			throws IOException {
		int id = ParamUtils.getInt(request, "id", 0);
		String action = ParamUtils.getString(request, "action", "");		
		if("listRecentArticles".equals(action)){
			return listRecentArticles(request, response, actionForward);
		}else if("listArticleTypes".equals(action)){
			return listArticleTypes(request, response, actionForward);
		}else if(id == 0){
			return showAllArticle(request, response, actionForward);
		}else{
			return showArticle(request, response, actionForward);
		}
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

	/**
	 * 查看文章
	 * @param request
	 * @param response
	 * @param forward
	 * @return
	 */
	private String showArticle(HttpServletRequest request,
			HttpServletResponse response, ActionForward forward) {
		try {
			int id = ParamUtils.getInt(request, "id", 0);
			ArticleVo article = articleManageService.findArticle(id);
			if(null == article){
				return forward.findForward("inexistence");
			}
			request.setAttribute("article", article);
			return forward.findForward("show");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
