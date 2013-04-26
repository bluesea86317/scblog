package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sc.auth.core.JDBCDaoSupport;
import com.sc.auth.vo.ArticleVo;

public class ArticleDao extends JDBCDaoSupport {
	
	public static ArticleDao getInstance(){
		return new ArticleDao();
	}
	
	public int addArticle(ArticleVo article) throws SQLException{
		return getJdbcDaoTemplate().insert("Article.addArticle", article);
	}
	
	public boolean deleteArticle(Map<String,Object> param) throws SQLException{		
		int count = getJdbcDaoTemplate().delete("Article.deleteArticle", param);
		return count > 0 ? true : false;
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleVo> queryRecentArticles(Map<String,Object> param) throws SQLException{
		List<ArticleVo> articles;
		param.put("recentCount", 5);
		articles = (List<ArticleVo>)getJdbcDaoTemplate().queryForList("Article.queryRecentArticles", param);
		return articles;
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleVo> queryArticles(Map<String,Object> param) throws SQLException{
		List<ArticleVo> articles;
		articles = (List<ArticleVo>)getJdbcDaoTemplate().queryForList("Article.queryArticles");
		return articles;
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleVo> queryArticlesByType(int articleType) throws SQLException{
		List<ArticleVo> articles;
		articles = (List<ArticleVo>)getJdbcDaoTemplate().queryForList("Article.queryArticlesByType", articleType);
		return articles;
	}
	
	public ArticleVo findArticle(int articleId) throws SQLException{		
		return (ArticleVo)getJdbcDaoTemplate().queryForObject("Article.findArticle", articleId);
	}

	public void updateArticle(ArticleVo article) throws SQLException {		
		getJdbcDaoTemplate().update("Article.updateArticle", article);
	}
}
