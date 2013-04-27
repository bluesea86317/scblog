package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.List;

import com.sc.auth.core.DaoSupport;
import com.sc.auth.core.JDBCDaoSupport;
import com.sc.auth.core.TestJDBCDaoSupport;
import com.sc.auth.vo.ArticleVo;

public class ExcSqlDao extends JDBCDaoSupport {
	
	/**
	 * 单例, 饥汉模式
	 */
	public static ExcSqlDao getInstance(){
		return new ExcSqlDao();
	}
	
	
	public List<ArticleVo> queryArticles() throws SQLException{
		return (List<ArticleVo>)getJdbcDaoTemplate().queryForList("Article.queryArticles");
	}
	
	
}
