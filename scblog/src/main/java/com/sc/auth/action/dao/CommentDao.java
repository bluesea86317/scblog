package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.sc.auth.vo.CommentVo;

public class CommentDao extends SqlMapClientDaoSupport{
	
	public static CommentDao getInstance(){
		return new CommentDao();
	}
	
	public void addComment(CommentVo comment) throws SQLException{		
		getSqlMapClientTemplate().insert("Comment.addComment", comment);
	}
	
	public boolean updateCommentStatus(int commentId) throws SQLException{
		return getSqlMapClientTemplate().update("Comment.updateCommentStatus", commentId) != 0 ? true : false;
	}
	
	public boolean deleteComment(int commentId) throws SQLException{
		return getSqlMapClientTemplate().delete("Comment.deleteComment", commentId) != 0 ? true : false;
	}
	
	/**
	 * 查询所有评论
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<CommentVo> queryComments() throws SQLException{
		return getSqlMapClientTemplate().queryForList("Comment.queryComments");
	}
	
	/**
	 * 获取某篇文章的所有评论
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<CommentVo> findCommentByArticleId(int articleId) throws SQLException{
		return getSqlMapClientTemplate().queryForList("Comment.findCommentByArticleId", articleId);
	}
}
