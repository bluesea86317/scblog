package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sc.auth.core.JDBCDaoSupport;
import com.sc.auth.vo.CommentVo;

public class CommentDao extends JDBCDaoSupport{
	
	public static CommentDao getInstance(){
		return new CommentDao();
	}
	
	public int addComment(CommentVo comment) throws SQLException{		
		return getJdbcDaoTemplate().insert("Comment.addComment", comment);
	}
	
	public boolean updateCommentStatus(int commentId) throws SQLException{
		return getJdbcDaoTemplate().update("Comment.updateCommentStatus", commentId) != 0 ? true : false;
	}
	
	public boolean deleteComment(int commentId) throws SQLException{
		return getJdbcDaoTemplate().delete("Comment.deleteComment", commentId) != 0 ? true : false;
	}
	
	/**
	 * 查询所有评论
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<CommentVo> queryComments() throws SQLException{
		return getJdbcDaoTemplate().queryForList("Comment.queryComments");
	}
	
	/**
	 * 获取某篇文章的所有评论
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<CommentVo> findCommentByArticleId(int articleId) throws SQLException{
		return getJdbcDaoTemplate().queryForList("Comment.findCommentByArticleId", articleId);
	}
}
