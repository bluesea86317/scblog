package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sc.auth.vo.ArticleType;

public class ArticleTypeDao extends SqlMapClientDaoSupport {
	
	public static ArticleTypeDao getInstance(){
		return new ArticleTypeDao();
	}
	
	public int addArticleType(ArticleType articleType) throws SQLException{
		return (Integer)getSqlMapClientTemplate().insert("ArticleType.addArticleType", articleType);
	}
	
	public boolean updateArticleType(ArticleType articleType) throws SQLException{
		return getSqlMapClientTemplate().update("ArticleType.updateArticleType", articleType) != 0 ? true : false;
	}
	
	public boolean deleteArticleType(int typeId) throws SQLException{
		return getSqlMapClientTemplate().delete("ArticleType.deleteArticleType",typeId) != 0 ? true : false;
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleType> queryArticleType() throws SQLException{
		List<ArticleType> articleTypeList = getSqlMapClientTemplate().queryForList("ArticleType.queryArticleType");
		return articleTypeList;
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleType> queryArticleTypeCount() throws SQLException{
		List<ArticleType> articleTypeList = getSqlMapClientTemplate().queryForList("ArticleType.queryArticleTypeCount");
		return articleTypeList;
	}
}
