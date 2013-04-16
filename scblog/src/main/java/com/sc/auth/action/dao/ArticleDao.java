package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sc.auth.core.DaoSupport;
import com.sc.auth.vo.ArticleVo;

public class ArticleDao extends DaoSupport {
	
	public static ArticleDao getInstance(){
		return new ArticleDao();
	}
	
	public int addArticle(ArticleVo article) throws SQLException{
		String insertSql = "insert into t_article (title, intro, content, authorId, createTime, lastModifyTime, articleType) " +
				"values (#title#, #intro#, #content#, #authorId#, #createTime#, #lastModifyTime#, #articleType#)";
		return insert(insertSql, article);
	}
	
	public boolean deleteArticle(Map<String,Object> param) throws SQLException{		
		boolean flag = true;		
		String deleteSql = "delete from t_article where id = #id#";		
		flag = delete(deleteSql, param);
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleVo> queryRecentArticles(Map<String,Object> param) throws SQLException{
		List<ArticleVo> articles;
		String sqlMapConfig = "select * from t_article order by id desc limit #recentCount#";
		articles = (List<ArticleVo>)queryForList(sqlMapConfig, param, ArticleVo.class);
		return articles;
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleVo> queryArticles(Map<String,Object> param) throws SQLException{
		List<ArticleVo> articles;
		String sqlMapConfig = "select * from t_article order by id desc";
		articles = (List<ArticleVo>)queryForList(sqlMapConfig, param, ArticleVo.class);
		return articles;
	}
	
	public ArticleVo findArticle(Map<String,Object> param) throws SQLException{
		String sqlMapConfig = "select * from t_article where id = #id#";
		return queryForObject(sqlMapConfig, param, ArticleVo.class);
	}
}
