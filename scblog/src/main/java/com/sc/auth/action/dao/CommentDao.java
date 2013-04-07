package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sc.auth.core.DaoSupport;
import com.sc.auth.vo.CommentVo;

public class CommentDao extends DaoSupport{
	
	public static CommentDao getInstance(){
		return new CommentDao();
	}
	
	public int addComment(CommentVo comment) throws SQLException{
		String sql = "insert into t_comment (id, visitor, email, website, comment, articleId, createTime, followedId, status) " +
				"values (#id#, #visitor#, #email#, #website#, #comment#, #articleId#, #createTime#, #followedId#, #status#)";
		return insert(sql, comment);
	}
	
	public boolean updateCommentStatus(Map<String, Object> param) throws SQLException{
		String sql = "update t_comment set status = 1 where id = #id#";
		return update(sql, param);
	}
	
	public boolean deleteComment(Map<String, Object> param) throws SQLException{
		String sql = "delete from t_comment where id = #id#";
		return delete(sql, param);
	}
	
	/**
	 * 查询所有评论
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<CommentVo> queryComments(Map<String, Object> param) throws SQLException{
		String sql = "select * from t_comment";
		return queryForList(sql, param, CommentVo.class);
	}
	
	/**
	 * 获取某篇文章的所有评论
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<CommentVo> findCommentByArticleId(Map<String, Object> param) throws SQLException{
		String sql = "select * from t_comment where articleId = #articleId# and status = 1";
		return queryForList(sql, param, CommentVo.class);
	}
}
