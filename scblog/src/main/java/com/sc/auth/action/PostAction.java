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
	
	@Override
	public String excute(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward)
			throws IOException {				
		try {
			int id = ParamUtils.getInt(request, "id", 0);
			ArticleVo article = articleManageService.findArticle(id);
			if(null == article){
				return actionForward.findForward("inexistence");
			}
			request.setAttribute("article", article);
			return actionForward.findForward("show");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return actionForward.findForward("inexistence");		
	}

}
