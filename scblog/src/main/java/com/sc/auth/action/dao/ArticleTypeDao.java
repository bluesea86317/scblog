package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sc.auth.core.DaoSupport;
import com.sc.auth.core.JBCDaoSupport;
import com.sc.auth.vo.ArticleType;

public class ArticleTypeDao extends JBCDaoSupport {
	
	public static ArticleTypeDao getInstance(){
		return new ArticleTypeDao();
	}
	
	public int addArticleType(ArticleType articleType) throws SQLException{
		return insert("ArticleType.addArticleType", articleType);
	}
	
	public boolean updateArticleType(ArticleType articleType) throws SQLException{
		return update("ArticleType.updateArticleType", articleType) != 0 ? true : false;
	}
	
	public boolean deleteArticleType(int typeId) throws SQLException{
		return delete("ArticleType.deleteArticleType",typeId) != 0 ? true : false;
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleType> queryArticleType() throws SQLException{
		List<ArticleType> articleTypeList = queryForList("ArticleType.queryArticleType");
		return articleTypeList;
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleType> queryArticleTypeCount() throws SQLException{
		List<ArticleType> articleTypeList = queryForList("ArticleType.queryArticleTypeCount");
		return articleTypeList;
	}
}
