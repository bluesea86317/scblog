package com.sc.auth.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sc.auth.action.dao.ArticleTypeDao;
import com.sc.auth.vo.ArticleType;

public class ArticleTypeService {
	
	private ArticleTypeDao articleTypeDao;
	
	public void addArticleType(ArticleType articleType) throws SQLException{
		getArticleTypeDao().addArticleType(articleType);
	}
	
	public boolean updateArticleType(ArticleType articleType) throws SQLException{
		return getArticleTypeDao().updateArticleType(articleType);
	}
	
	public boolean deleteArticleType(int id) throws SQLException{
		return getArticleTypeDao().deleteArticleType(id);
	}
	
	public List<ArticleType> queryArtilcType() throws SQLException{
		return getArticleTypeDao().queryArticleType();
	}
	
	public List<ArticleType> queryArticleTypeCount() throws SQLException{
		return getArticleTypeDao().queryArticleTypeCount();
	}

	public ArticleTypeDao getArticleTypeDao() {
		return articleTypeDao;
	}

	public void setArticleTypeDao(ArticleTypeDao articleTypeDao) {
		this.articleTypeDao = articleTypeDao;
	}
}
