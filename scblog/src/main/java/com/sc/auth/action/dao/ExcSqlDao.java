package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sc.auth.core.DaoSupport;
import com.sc.auth.core.JDBCDaoSupport;
import com.sc.auth.vo.ArticleVo;

public class ExcSqlDao extends SqlMapClientDaoSupport {
	
	/**
	 * 单例, 饥汉模式
	 */
	public static ExcSqlDao getInstance(){
		return new ExcSqlDao();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ArticleVo> queryArticles() throws SQLException{
		return (List<ArticleVo>)getSqlMapClientTemplate().queryForList("Article.queryArticles");
	}
	
	
}
