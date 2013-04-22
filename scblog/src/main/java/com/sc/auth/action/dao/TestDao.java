package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.List;

import com.sc.auth.core.TestDaoSupport;
import com.sc.auth.vo.ArticleVo;

public class TestDao extends TestDaoSupport {

	public static TestDao getInstance(){
		return new TestDao();
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleVo> queryArticles() throws SQLException{
		return queryForList("Article.queryArticles", null);
	}
}
