package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sc.auth.core.JDBCDaoSupport;
import com.sc.auth.vo.ArticleType;

public class ArticleTypeDao extends JDBCDaoSupport {
	
	public static ArticleTypeDao getInstance(){
		return new ArticleTypeDao();
	}
	
	public int addArticleType(ArticleType articleType) throws SQLException{
		return getJdbcDaoTemplate().insert("ArticleType.addArticleType", articleType);
	}
	
	public boolean updateArticleType(ArticleType articleType) throws SQLException{
		return getJdbcDaoTemplate().update("ArticleType.updateArticleType", articleType) != 0 ? true : false;
	}
	
	public boolean deleteArticleType(int typeId) throws SQLException{
		return getJdbcDaoTemplate().delete("ArticleType.deleteArticleType",typeId) != 0 ? true : false;
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleType> queryArticleType() throws SQLException{
		List<ArticleType> articleTypeList = getJdbcDaoTemplate().queryForList("ArticleType.queryArticleType");
		return articleTypeList;
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleType> queryArticleTypeCount() throws SQLException{
		List<ArticleType> articleTypeList = getJdbcDaoTemplate().queryForList("ArticleType.queryArticleTypeCount");
		return articleTypeList;
	}
}
