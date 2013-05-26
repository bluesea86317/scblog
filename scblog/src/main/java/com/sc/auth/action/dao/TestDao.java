package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sc.auth.core.JDBCDaoSupport;
import com.sc.auth.vo.ArticleVo;

public class TestDao extends SqlMapClientDaoSupport {

	public static TestDao getInstance(){
		return new TestDao();
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleVo> queryArticles() throws SQLException{
		return (List<ArticleVo>)getSqlMapClientTemplate().queryForList("Article.queryArticles", null);
	}
}
