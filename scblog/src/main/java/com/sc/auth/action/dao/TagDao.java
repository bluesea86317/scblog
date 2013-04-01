package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sc.auth.core.DaoSupport;
import com.sc.auth.vo.Tag;

public class TagDao extends DaoSupport {
	
	public static TagDao getInstance(){
		return new TagDao();
	}
	/**
	 * 新增标签
	 * @param tag
	 * @return
	 * @throws SQLException
	 */
	public int addTag(Tag tag) throws SQLException{
		String sql = "insert into t_tag (tagName) values (#tagName#)";
		return insert(sql, tag);
	}
	
	/**
	 * 新增文章的标签
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	public int addArticleTagRelation(int articleId, int tagId) throws SQLException{
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("articleId", articleId);
		param.put("tagId", tagId);
		String sql = "insert into t_article_tag (articleId, tagId) values (#articleId#, #tagId#)";
		return insert(sql, param);
	}
	
	/**
	 * 通过标签内容查询标签
	 * @param tagName
	 * @return
	 * @throws SQLException
	 */
	public Tag findTag(String tagName) throws SQLException{
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("tagName", tagName);
		String sql = "select * from t_tag where tagName = #tagName#";
		return (Tag)queryForObject(sql, param, Tag.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tag> queryTagsByArticleId(int articleId) throws SQLException{
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("articleId", articleId);
		String sql = "select tt.* from t_article_tag at left join t_tag tt on at.tagId = tt.id where at.articleId = #articleId#";
		return (List<Tag>)queryForList(sql, param, Tag.class);
	}
}
