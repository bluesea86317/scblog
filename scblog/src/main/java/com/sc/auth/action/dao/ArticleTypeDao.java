package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sc.auth.core.DaoSupport;
import com.sc.auth.vo.ArticleType;

public class ArticleTypeDao extends DaoSupport {
	
	public static ArticleTypeDao getInstance(){
		return new ArticleTypeDao();
	}
	
	public int addArticleType(ArticleType articleType) throws SQLException{
		String sql = "insert into t_article_type (typeName) values (#typeName#)";
		return insert(sql, articleType);
	}
	
	public boolean updateArticleType(Map<String,Object> param) throws SQLException{
		String sql = "update t_article_type set typeName = #typeName# where id = #id#";
		return update(sql, param);
	}
	
	public boolean deleteArticleType(Map<String,Object> param) throws SQLException{
		String sql = "delete from t_article_type where id = #id#";
		return delete(sql, param);
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleType> queryArticleType(Map<String,Object> param) throws SQLException{
		String sql = "select * from t_article_type";
		List<ArticleType> articleTypeList = queryForList(sql, param, ArticleType.class);
		return articleTypeList;
	}
}
