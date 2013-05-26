package com.sc.auth.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.sc.auth.core.Action;
import com.sc.auth.core.ActionForward;
import com.sc.auth.core.Env;
import com.sc.auth.service.ArticleTypeService;
import com.sc.auth.util.ParamUtils;
import com.sc.auth.vo.ArticleType;

public class ArticleTypeManageAction extends Action {

	private ArticleTypeService articleTypeService = Env.getBean("articleTypeService");
	@Override
	public String excute(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward)
			throws IOException {
		String action = ParamUtils.getString(request, "action", "");
		if("add".equals(action)){
			return addArticleType(request,response,actionForward);
		}else if("update".equals(action)){
			return updateArticleType(request,response,actionForward);
		}else if("delete".equals(action)){
			return deleteArticleType(request,response,actionForward);
		}else if("ajax_list".equals(action)){
			return ajaxListArticleType(request,response,actionForward);
		}else{			
			return listArticleType(request,response,actionForward);
		}
	}

	private String ajaxListArticleType(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {
		try {
			List<ArticleType> articleTypeList = articleTypeService.queryArtilcType();
			JSONArray jsonArray = new JSONArray();
			jsonArray.addAll(articleTypeList);
			outPut(response, jsonArray.toString());
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return null;
	}

	private String deleteArticleType(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {
		try {
			int id = ParamUtils.getInt(request, "typeId", 0);
			articleTypeService.deleteArticleType(id);
			return_out(response, PROCESS_RESULT_SUCCESS, "删除成功! ");
		} catch (SQLException e) {			
			e.printStackTrace();
			return_out(response, PROCESS_RESULT_FAILURE, "删除失败, 错误原因: " + e.getMessage());
		}
		return null;
	}

	private String updateArticleType(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {
		try {
			String typeName = ParamUtils.getString(request, "typeName", "");
			int id = ParamUtils.getInt(request, "typeId", 0);
			ArticleType articleType = new ArticleType();
			articleType.setId(id);
			articleType.setTypeName(typeName);
			articleTypeService.updateArticleType(articleType);
			return_out(response, PROCESS_RESULT_SUCCESS, "修改成功! ");
		} catch (SQLException e) {			
			e.printStackTrace();
			return_out(response, PROCESS_RESULT_FAILURE, "修改失败, 错误原因: " + e.getMessage());
		}
		return null;
	}

	private String addArticleType(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {
		try {
			String typeName = ParamUtils.getString(request, "typeName", "");
			ArticleType articleType = new ArticleType();
			articleType.setTypeName(typeName);
			articleTypeService.addArticleType(articleType);
			return_out(response, PROCESS_RESULT_SUCCESS, "添加成功! ");
		} catch (SQLException e) {			
			e.printStackTrace();
			return_out(response, PROCESS_RESULT_FAILURE, "添加失败, 错误原因: " + e.getMessage());
		}
		return null;
	}

	private String listArticleType(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {
		try {
			List<ArticleType> articleTypeList = articleTypeService.queryArtilcType();
			request.setAttribute("articleTypeList", articleTypeList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actionForward.findForward("list");
	}

}
