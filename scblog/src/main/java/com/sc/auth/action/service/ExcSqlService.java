package com.sc.auth.action.service;

import java.sql.SQLException;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.sc.auth.action.dao.ExcSqlDao;

public class ExcSqlService {	
	
	private DataSourceTransactionManager transactionManager;
	private final static ExcSqlService excSqlService = new ExcSqlService();
	private ExcSqlDao excSqlDao = ExcSqlDao.getInstance();	
	private DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	
	private DataSourceTransactionManager createTransactionManager(){
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
//		transactionManager.setDataSource(excSqlDao.getDataSource());
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return transactionManager;
	}
	
	public boolean excuteSql(String sql){
		transactionManager = createTransactionManager();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			return excSqlDao.excuteSql(sql);
		} catch (SQLException e) {
			transactionManager.rollback(status);
			e.printStackTrace();
		}
		return false;
	}
	
	public static ExcSqlService getInstance() {
		return excSqlService;
	}
}
