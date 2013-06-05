package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sc.auth.vo.ArticleVo;

public class ArticleDao extends SqlMapClientDaoSupport{
	
	public static ArticleDao getInstance(){
		return new ArticleDao();
	}
	
	public int addArticle(ArticleVo article) throws SQLException{
		return (Integer)getSqlMapClientTemplate().insert("Article.addArticle", article);
	}
	
	public boolean deleteArticle(int id) throws SQLException{		
		int count = getSqlMapClientTemplate().delete("Article.deleteArticle", id);
		return count > 0 ? true : false;
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleVo> queryRecentArticles(Map<String,Object> param) throws SQLException{
		List<ArticleVo> articles;
		param.put("recentCount", 5);
		articles = (List<ArticleVo>)getSqlMapClientTemplate().queryForList("Article.queryRecentArticles", param);
		return articles;
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleVo> queryArticles(Map<String,Object> param) throws SQLException{
		List<ArticleVo> articles;
		articles = (List<ArticleVo>)getSqlMapClientTemplate().queryForList("Article.queryArticles");
		return articles;
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleVo> queryArticlesByType(int articleType) throws SQLException{
		List<ArticleVo> articles;
		articles = (List<ArticleVo>)getSqlMapClientTemplate().queryForList("Article.queryArticlesByType", articleType);
		return articles;
	}
	
	public ArticleVo findArticle(int articleId) throws SQLException{		
		return (ArticleVo)getSqlMapClientTemplate().queryForObject("Article.findArticle", articleId);
	}

	public void updateArticle(ArticleVo article) throws SQLException {		
		getSqlMapClientTemplate().update("Article.updateArticle", article);
	}

	@SuppressWarnings("unchecked")
	public List<ArticleVo> searchArticles(String searchValue) {
		List<ArticleVo> articles;
		articles = (List<ArticleVo>)getSqlMapClientTemplate().queryForList("Article.searchArticles", searchValue);
		return articles;
	}
}
