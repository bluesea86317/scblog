package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sc.auth.core.DaoSupport;
import com.sc.auth.core.TestDaoSupport;
import com.sc.auth.vo.ArticleVo;

public class ArticleDao extends TestDaoSupport {
	
	public static ArticleDao getInstance(){
		return new ArticleDao();
	}
	
	public int addArticle(ArticleVo article) throws SQLException{
		return insert("Article.addArticle", article);
	}
	
	public boolean deleteArticle(Map<String,Object> param) throws SQLException{		
		int count = delete("Article.deleteArticle", param);
		return count > 0 ? true : false;
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleVo> queryRecentArticles(Map<String,Object> param) throws SQLException{
		List<ArticleVo> articles;
		param.put("recentCount", 5);
		articles = (List<ArticleVo>)queryForList("Article.queryRecentArticles", param);
		return articles;
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleVo> queryArticles(Map<String,Object> param) throws SQLException{
		List<ArticleVo> articles;
		articles = (List<ArticleVo>)queryForList("Article.queryArticles");
		return articles;
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleVo> queryArticlesByType(int articleType) throws SQLException{
		List<ArticleVo> articles;
		articles = (List<ArticleVo>)queryForList("Article.queryArticlesByType", articleType);
		return articles;
	}
	
	public ArticleVo findArticle(int articleId) throws SQLException{		
		return (ArticleVo)queryForObject("Article.findArticle", articleId);
	}

	public void updateArticle(ArticleVo article) throws SQLException {
//		Map<String,Object> param = new HashMap<String,Object>();
//		param.put("id", article.getId());
//		param.put("intro", article.getIntro());
//		param.put("title", article.getTitle());
//		param.put("content", article.getContent());
//		param.put("authorId", article.getAuthorId());
//		param.put("lastModifyTime", article.getLastModifyTime());
//		param.put("articleType", article.getArticleType());
//		String sql = "update t_article set title = #title#, intro = #intro#, content = #content#, authorId = #authorId#, lastModifyTime = #lastModifyTime#, articleType = #articleType# where id = #id#";
		update("Article.updateArticle", article);
	}
}
