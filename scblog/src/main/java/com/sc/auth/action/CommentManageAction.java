package com.sc.auth.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.sc.auth.action.service.CommentService;
import com.sc.auth.core.Action;
import com.sc.auth.core.ActionForward;
import com.sc.auth.util.ParamUtils;
import com.sc.auth.vo.CommentVo;

public class CommentManageAction extends Action{

	private CommentService commentService = CommentService.getInstance();
	@Override
	public String excute(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward)
			throws IOException {
		String action = ParamUtils.getString(request, "action", "");
		if("add".equals(action)){
			return addComment(request, response, actionForward);
		}else if("update".equals(action)){
			return update(request, response, actionForward);
		}else if("delete".equals(action)){
			return delete(request, response, actionForward);
		}else if("showComment".equals(action)){
			return showComment(request, response, actionForward);
		}else{
			return queryComments(request, response, actionForward);
		}
	}

	
	private String showComment(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {
		int articleId = ParamUtils.getInt(request, "", 0);
		try {
			List<CommentVo> comments = commentService.findComments(articleId);
			JSONArray jsonArray = new JSONArray();
			jsonArray.addAll(comments);			
			outPut(response, jsonArray.toString());
		} catch (SQLException e) {			
			e.printStackTrace();
			return_out(response, PROCESS_RESULT_FAILURE, e.getMessage());
		}
		return null;
	}


	private String queryComments(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {
		List<CommentVo> comments;
		try {
			comments = commentService.queryComments();
			request.setAttribute("comments", comments);
			return actionForward.findForward("list");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}

	private String delete(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {
		int id = ParamUtils.getInt(request, "id", 0);
		try {
			commentService.deleteComment(id);
			return_out(response, PROCESS_RESULT_SUCCESS, "删除成功");
		} catch (SQLException e) {			
			e.printStackTrace();
			return_out(response, PROCESS_RESULT_FAILURE, "删除失败，错误信息： " + e.getMessage());
		}
		return null;
	}

	private String update(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {
		int id = ParamUtils.getInt(request, "id", 0);
		try {
			commentService.updateCommentStatus(id);
			return_out(response, PROCESS_RESULT_SUCCESS, "审核成功");
		} catch (SQLException e) {			
			e.printStackTrace();
			return_out(response, PROCESS_RESULT_FAILURE, "审核失败，错误信息： " + e.getMessage());
		}
		return null;
	}

	private String addComment(HttpServletRequest request,
			HttpServletResponse response, ActionForward actionForward) {
		String commentContent = ParamUtils.getString(request, "commentContent", "");
		int articleId = ParamUtils.getInt(request, "articleId", 0);
		int followedId = ParamUtils.getInt(request, "followedId", 0);	
		String visitor = ParamUtils.getString(request, "visitor", "");
		String email = ParamUtils.getString(request, "email", "");
		String website = ParamUtils.getString(request, "website", "");
		try {
			CommentVo comment = new CommentVo();
			comment.setVisitor(visitor);
			comment.setEmail(email);
			comment.setWebsite(website);
			comment.setComment(commentContent);
			comment.setArticleId(articleId);
			comment.setFollowedId(followedId);
			comment.setCreateTime(new Date());
			comment.setStatus(CommentVo.STATUS_UNVERIFIED);
			commentService.addComment(comment);
			return_out(response, PROCESS_RESULT_SUCCESS, "评论成功");
		} catch (SQLException e) {			
			e.printStackTrace();
			return_out(response, PROCESS_RESULT_FAILURE, "评论失败，错误信息： " + e.getMessage());
		}
		return null;
	}
	
	
}
