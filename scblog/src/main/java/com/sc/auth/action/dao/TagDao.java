package com.sc.auth.action.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sc.auth.core.DaoSupport;
import com.sc.auth.core.JBCDaoSupport;
import com.sc.auth.vo.Tag;

public class TagDao extends JBCDaoSupport {
	
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
		return insert("Tag.addTag", tag);
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
		return insert("Tag.addArticleTagRelation", param);
	}
	
	/**
	 * 通过标签内容查询标签
	 * @param tagName
	 * @return
	 * @throws SQLException
	 */
	public Tag findTag(String tagName) throws SQLException{		
		return (Tag)queryForObject("Tag.findTag", tagName);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tag> queryTagsByArticleId(int articleId) throws SQLException{
		return (List<Tag>)queryForList("Tag.queryTagsByArticleId", articleId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tag> queryTags() throws SQLException{
		return (List<Tag>)queryForList("Tag.queryTags");
	}
	
	public boolean deleteTag(int tagId) throws SQLException {
		return delete("Tag.deleteTag", tagId) != 0 ? true : false;
	}
	
	public boolean deleteArticleTagRelation(int tagId) throws SQLException {
		return delete("Tag.deleteArticleTagRelation", tagId) != 0 ? true : false;
	}
	
	public boolean updateTag(Tag tag) throws SQLException {
		return update("Tag.updateTag", tag) != 0 ? true : false;
	}
	
	public boolean deleteRelationByArticleId(int articleId) throws SQLException {
		return delete("Tag.deleteRelationByArticleId", articleId) != 0? true : false;
	}
	
	@SuppressWarnings("unchecked")
	public List<Tag> queryTagsByArticleIdAndTagId(int articleId, int tagId) throws SQLException {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("articleId", articleId);
		param.put("tagId", tagId);		
		return (List<Tag>)queryForList("Tag.queryTagsByArticleIdAndTagId", param);
	}
}
