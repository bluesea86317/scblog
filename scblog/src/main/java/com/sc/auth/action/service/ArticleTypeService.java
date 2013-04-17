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
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", articleType.getId());
		param.put("typeName", articleType.getTypeName());
		return articleTypeDao.updateArticleType(param);
	}
	
	public boolean deleteArticleType(int id) throws SQLException{
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", id);
		return articleTypeDao.deleteArticleType(param);
	}
	
	public List<ArticleType> queryArtilcType() throws SQLException{
		Map<String,Object> param = new HashMap<String,Object>();
		return articleTypeDao.queryArticleType(param);
	}
}
