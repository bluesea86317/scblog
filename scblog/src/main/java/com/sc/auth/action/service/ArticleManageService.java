package com.sc.auth.action.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sc.auth.action.dao.ArticleDao;
import com.sc.auth.vo.ArticleVo;
import com.sc.auth.vo.Tag;

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
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("id", id);
		ArticleVo article = dao.findArticle(param);
		article.setTags(tagManageService.queryTagsByArticleId(article.getId()));
		return article;
	}
	
	/**
	 * 文章列表
	 * @return
	 * @throws SQLException
	 */
	public List<ArticleVo> queryArticles() throws SQLException{
		Map<String,Object> param = new HashMap<String, Object>();
		List<ArticleVo> articles = dao.queryArticles(param);
		for(int i =0; i< articles.size(); i++){
			articles.get(i).setTags(tagManageService.queryTagsByArticleId(articles.get(i).getId()));
		}
		return articles;
	}
}
