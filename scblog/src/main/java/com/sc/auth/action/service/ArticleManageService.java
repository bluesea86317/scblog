package com.sc.auth.action.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sc.auth.action.dao.ArticleDao;
import com.sc.auth.vo.ArticleVo;

public class ArticleManageService {

	private ArticleDao dao = ArticleDao.getInstance();
	private TagManageService tagManageService = TagManageService.getInstance();
	
	public static ArticleManageService getInstance(){
		return new ArticleManageService();
	}
	
	/**
	 * 新增文章
	 * @param article
	 * @return
	 * @throws SQLException
	 */
	public void addArticle(ArticleVo article, String tagStr) throws SQLException{
		int articleId = dao.addArticle(article);
		addArticleTag(articleId, tagStr);
	}

	public void updateArticle(ArticleVo article, String tagStr) throws SQLException{
		dao.updateArticle(article);
		addArticleTag(article.getId(), tagStr);
	}
	
	public void addArticleTag(int articleId, String tagStr) throws SQLException{
		tagManageService.addArticleTag(articleId, tagStr);
	}
	
	/**
	 * 删除文章
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public boolean deleteArticle(int id) throws SQLException{
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("id", id);
		return dao.deleteArticle(param);
	}
	
	public ArticleVo findArticle(int id) throws SQLException{		
		ArticleVo article = dao.findArticle(id);
		article.setTags(tagManageService.queryTagsByArticleId(article.getId()));
		return article;
	}
	
	public List<ArticleVo> queryRecentArticles() throws SQLException{
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("recentCount", 5);
		List<ArticleVo> articles = dao.queryRecentArticles(param);		
		return articles;
	}
	
	/**
	 * 文章列表
	 * @return
	 * @throws SQLException
	 */
	public List<ArticleVo> queryArticles() throws SQLException{
		Map<String,Object> param = new HashMap<String, Object>();
		List<ArticleVo> articles = dao.queryArticles(param);
		return articles;
	}
	
	/**
	 * 通过文章类型列出文章列表
	 * @return
	 * @throws SQLException
	 */
	public List<ArticleVo> queryArticlesByType(int articleType) throws SQLException{
		List<ArticleVo> articles = dao.queryArticlesByType(articleType);
		return articles;
	}
	
}
