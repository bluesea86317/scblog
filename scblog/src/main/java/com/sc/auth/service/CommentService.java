package com.sc.auth.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sc.auth.action.dao.CommentDao;
import com.sc.auth.vo.CommentVo;

public class CommentService {
	
	private CommentDao commentDao;
	
	/**
	 * 新增评论
	 * @param comment
	 * @return
	 * @throws SQLException
	 */
	public int addComment(CommentVo comment) throws SQLException{		
		return getCommentDao().addComment(comment);
	}
	
	/**
	 * 评论审核
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean updateCommentStatus(int id) throws SQLException{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		return getCommentDao().updateCommentStatus(id);
	}
	
	/**
	 * 删除评论
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteComment(int id) throws SQLException{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		return getCommentDao().deleteComment(id);
	}
	
	/**
	 * 列出所有评论
	 * @return
	 * @throws SQLException
	 */
	public List<CommentVo> queryComments() throws SQLException{
		return getCommentDao().queryComments();
	}
	
	/**
	 * 找出某篇文章的所有评论
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<CommentVo> findComments(int articleId) throws SQLException{
		return getCommentDao().findCommentByArticleId(articleId);
	}
	public CommentDao getCommentDao() {
		return commentDao;
	}
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
}
