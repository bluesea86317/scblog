package com.sc.auth.action.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sc.auth.action.dao.ArticleDao;
import com.sc.auth.vo.ArticleVo;

public class ArticleManageService {

	private ArticleDao dao = ArticleDao.getInstance();

	public static ArticleManageService getInstance(){
		return new ArticleManageService();
	}
	
	/**
	 * 新增文章
	 * @param article
	 * @return
	 * @throws SQLException
	 */
	public boolean addArticle(ArticleVo article) throws SQLException{
		return dao.addArticle(article);
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
		return dao.findArticle(param);
	}
	
	public List<ArticleVo> queryArticles() throws SQLException{
		Map<String,Object> param = new HashMap<String, Object>();
		return dao.queryArticles(param);
	}
}
