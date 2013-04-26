package com.sc.auth.action.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.sc.auth.action.dao.ExcSqlDao;
import com.sc.auth.vo.ArticleVo;

public class ExcSqlService {	
	
	
	private ExcSqlDao excSqlDao = ExcSqlDao.getInstance();	
	
	
	public List<ArticleVo> excuteSql(String sql) throws SQLException{
		return excSqlDao.queryArticles();
	}
	
	public static ExcSqlService getInstance() {
		return new ExcSqlService();
	}
}
