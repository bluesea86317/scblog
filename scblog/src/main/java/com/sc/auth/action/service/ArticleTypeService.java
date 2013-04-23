package com.sc.auth.action.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sc.auth.action.dao.ArticleTypeDao;
import com.sc.auth.vo.ArticleType;

public class ArticleTypeService {
	
	public ArticleTypeDao articleTypeDao = ArticleTypeDao.getInstance();
	
	public static ArticleTypeService getInstance(){
		return new ArticleTypeService();
	}
	
	public int addArticleType(ArticleType articleType) throws SQLException{
		return articleTypeDao.addArticleType(articleType);
	}
	
	public boolean updateArticleType(ArticleType articleType) throws SQLException{
		return articleTypeDao.updateArticleType(articleType);
	}
	
	public boolean deleteArticleType(int id) throws SQLException{
		return articleTypeDao.deleteArticleType(id);
	}
	
	public List<ArticleType> queryArtilcType() throws SQLException{
		return articleTypeDao.queryArticleType();
	}
	
	public List<ArticleType> queryArticleTypeCount() throws SQLException{
		return articleTypeDao.queryArticleType();
	}
}
