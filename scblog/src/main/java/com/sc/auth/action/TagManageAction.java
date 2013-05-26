package com.sc.auth.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sc.auth.core.Action;
import com.sc.auth.core.ActionForward;
import com.sc.auth.core.Env;
import com.sc.auth.service.TagManageService;
import com.sc.auth.util.ParamUtils;
import com.sc.auth.vo.Tag;

public class TagManageAction extends Action {
	
	private TagManageService tagManageService = Env.getBean("tagManageService");
	@Override
	public String excute(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward)
			throws IOException {
		String action = ParamUtils.getString(request, "action", "");
		if("add".equals(action)){
			return addTag(request,response,actionForward);
		}else if("update".equals(action)){
			return updateTag(request,response,actionForward);
		}else if("delete".equals(action)){
			return deleteTag(request,response,actionForward);
		}else{			
			return listTag(request,response,actionForward);
		}
	}

	private String listTag(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {
		try {
			List<Tag> tagList = tagManageService.queryTags();
			request.setAttribute("tagList", tagList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actionForward.findForward("list");
	}

	private String deleteTag(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {
		try {
			int id = ParamUtils.getInt(request, "tagId", 0);
			tagManageService.deleteTag(id);
			return_out(response, PROCESS_RESULT_SUCCESS, "删除成功! ");
		} catch (SQLException e) {			
			e.printStackTrace();
			return_out(response, PROCESS_RESULT_FAILURE, "删除失败, 错误原因: " + e.getMessage());
		}
		return null;
	}

	private String updateTag(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {		
		try {
			int id = ParamUtils.getInt(request, "tagId", 0);
			String tagName = ParamUtils.getString(request, "tagName", "");			
			tagManageService.updateTag(id, tagName);
			return_out(response, PROCESS_RESULT_SUCCESS, "修改成功! ");
		} catch (SQLException e) {			
			e.printStackTrace();
			return_out(response, PROCESS_RESULT_FAILURE, "修改失败, 错误原因: " + e.getMessage());
		}
		return null;
	}

	private String addTag(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {
		// TODO Auto-generated method stub
		return null;
	}

}
