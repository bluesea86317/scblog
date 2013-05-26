package com.sc.auth.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sc.auth.action.dao.TagDao;
import com.sc.auth.util.StringUtils;
import com.sc.auth.vo.Tag;

public class TagManageService {
	
	private TagDao tagDao;
	
	/**
	 * 新增文章的标签
	 * @param articleId
	 * @param tagStr
	 * @throws SQLException
	 */
	public void addArticleTag(int articleId, String tagStr) throws SQLException{
		String[] tags = tagStr.split(",");
//		先删除文章与标签的关联关系
		deleteRelationByArticleId(articleId);
		if(!StringUtils.isNullOrEmpty(tagStr.replace(",", ""))){
			for(String tagName : tags){
				Tag tag = getTagDao().findTag(tagName);
				if(tag == null){
					tag = new Tag();
					tag.setTagName(tagName);
					tag.setId(getTagDao().addTag(tag));
				}
//				再添加新的标签关联关系
				addArticleTagRelation(articleId, tag.getId());			
			}
		}
	}
	
	public void addArticleTagRelation(int articleId, int tagId) throws SQLException{		
		getTagDao().addArticleTagRelation(articleId, tagId);			
	}
	
	public void deleteRelationByArticleId(int articleId) throws SQLException{
//		Map<String,Object> param = new HashMap<String, Object>();
//		param.put("articleId", articleId);
		getTagDao().deleteRelationByArticleId(articleId);
	}
	
	public void deleteTag(int tagId) throws SQLException{
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("tagId", tagId);
		getTagDao().deleteTag(tagId);
		getTagDao().deleteArticleTagRelation(tagId);
	}
	
	public List<Tag> queryTags() throws SQLException{
		return getTagDao().queryTags();
	}
	
	public List<Tag> queryTagsByArticleIdAndTagId(int articleId, int tagId) throws SQLException{
		return getTagDao().queryTagsByArticleIdAndTagId(articleId, tagId);		
	}
	
	public List<Tag> queryTagsByArticleId(int articleId) throws SQLException{
		return getTagDao().queryTagsByArticleId(articleId);		
	}
	
	public void updateTag(int id, String tagName) throws SQLException {
//		Map<String,Object> param = new HashMap<String, Object>();
//		param.put("id", id);
//		param.put("tagName", tagName);
		Tag tag = new Tag();
		tag.setId(id);
		tag.setTagName(tagName);
		getTagDao().updateTag(tag);		
	}

	public TagDao getTagDao() {
		return tagDao;
	}

	public void setTagDao(TagDao tagDao) {
		this.tagDao = tagDao;
	}
}
