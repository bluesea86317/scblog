package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sc.auth.core.DaoSupport;
import com.sc.auth.vo.ArticleVo;

public class ArticleDao extends DaoSupport {
	
	/**
	 * 添加文章
	 * @param article
	 * @return
	 * @throws SQLException
	 */
	public boolean addArticle(ArticleVo article) throws SQLException{
		boolean flag = true;
		String insertSql = "insert into t_article (fid, ftitle, fcontent, fauthorId, fcreateTime, flastModifyTime, farticleType)";
		flag = insert(insertSql, article);
		return flag;
	}
	
	/**
	 * 获取文章列表
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<ArticleVo> queryArticles(Map<String,Object> param) throws SQLException{
		List<ArticleVo> articles;
		String sqlMapConfig = "select * from t_article ";
		articles = (List<ArticleVo>)queryForList(sqlMapConfig, param, ArticleVo.class);
		return articles;
	}
	
	/**
	 * 查看单个文章
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	public ArticleVo findArticle(Map<String,Object> param) throws SQLException{
		String sqlMapConfig = "select * from t_article where fid = #id#";
		return queryForObject(sqlMapConfig, param, ArticleVo.class);
	}
}
