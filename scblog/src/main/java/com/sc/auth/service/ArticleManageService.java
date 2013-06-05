package com.sc.auth.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sc.auth.action.dao.ArticleDao;
import com.sc.auth.vo.ArticleVo;

public class ArticleManageService {

	private ArticleDao articleDao;
	private TagManageService tagManageService;
	
	
	/**
	 * 新增文章
	 * @param article
	 * @return
	 * @throws SQLException
	 */
	public void addArticle(ArticleVo article, String tagStr) throws SQLException{
		int articleId = articleDao.addArticle(article);
		addArticleTag(articleId, tagStr);
	}

	public void updateArticle(ArticleVo article, String tagStr) throws SQLException{
		articleDao.updateArticle(article);
		addArticleTag(article.getId(), tagStr);
	}
	
	public void addArticleTag(int articleId, String tagStr) throws SQLException{
		getTagManageService().addArticleTag(articleId, tagStr);
	}
	
	/**
	 * 删除文章
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public boolean deleteArticle(int id) throws SQLException{		
		return articleDao.deleteArticle(id);
	}
	
	public ArticleVo findArticle(int id) throws SQLException{		
		ArticleVo article = articleDao.findArticle(id);
		article.setTags(getTagManageService().queryTagsByArticleId(article.getId()));
		return article;
	}
	
	public List<ArticleVo> queryRecentArticles() throws SQLException{
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("recentCount", 5);
		List<ArticleVo> articles = articleDao.queryRecentArticles(param);		
		return articles;
	}
	
	/**
	 * 文章列表
	 * @return
	 * @throws SQLException
	 */
	public List<ArticleVo> queryArticles() throws SQLException{
		Map<String,Object> param = new HashMap<String, Object>();
		List<ArticleVo> articles = articleDao.queryArticles(param);
		return articles;
	}
	
	/**
	 * 通过文章类型列出文章列表
	 * @return
	 * @throws SQLException
	 */
	public List<ArticleVo> queryArticlesByType(int articleType) throws SQLException{
		List<ArticleVo> articles = articleDao.queryArticlesByType(articleType);
		return articles;
	}

	public List<ArticleVo> searchArticles(String searchValue) throws SQLException{
		searchValue = "%"+searchValue+"%";
		List<ArticleVo> articles = articleDao.searchArticles(searchValue);
		return articles;
	}
	
	public TagManageService getTagManageService() {
		return tagManageService;
	}

	public void setTagManageService(TagManageService tagManageService) {
		this.tagManageService = tagManageService;
	}

	public ArticleDao getArticleDao() {
		return articleDao;
	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
}
