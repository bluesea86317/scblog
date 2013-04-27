package com.sc.auth.action.service;

import java.sql.SQLException;
import java.util.List;

import com.sc.auth.action.dao.ExcSqlDao;
import com.sc.auth.core.transaction.annoation.MethodTransactionConfig;
import com.sc.auth.vo.ArticleVo;

public class ExcSqlService {	
	
	
	private ExcSqlDao excSqlDao = ExcSqlDao.getInstance();	
	
	@MethodTransactionConfig(needControl = true)
	public List<ArticleVo> queryArticles() throws SQLException{
		return excSqlDao.queryArticles();
	}
	
	public static ExcSqlService getInstance() {
		return new ExcSqlService();
	}
}
