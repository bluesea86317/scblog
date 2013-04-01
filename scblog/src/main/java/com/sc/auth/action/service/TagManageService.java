package com.sc.auth.action.service;

import java.sql.SQLException;
import java.util.List;

import com.sc.auth.action.dao.TagDao;
import com.sc.auth.util.StringUtils;
import com.sc.auth.vo.Tag;

public class TagManageService {
	
	private TagDao tagDao = TagDao.getInstance();
	
	public static TagManageService getInstance(){
		return new TagManageService();
	}
	/**
	 * 新增文章的标签
	 * @param articleId
	 * @param tagStr
	 * @throws SQLException
	 */
	public void addArticleTag(int articleId, String tagStr) throws SQLException{
		String[] tags = tagStr.split(",");
		if(!StringUtils.isNullOrEmpty(tagStr.replace(",", ""))){
			for(String tagName : tags){
				Tag tag = tagDao.findTag(tagName);
				if(tag == null){
					tag = new Tag();
					tag.setTagName(tagName);
					tagDao.addTag(tag);
					tag = tagDao.findTag(tagName);
				}
				tagDao.addArticleTagRelation(articleId, tag.getId());			
			}
		}
	}
	
	public List<Tag> queryTagsByArticleId(int articleId) throws SQLException{
		return tagDao.queryTagsByArticleId(articleId);		
	}
}
