package com.sc.auth.vo;

import java.util.Date;
import java.util.List;

public class ArticleVo {
	/**
	 * 文章主键ID
	 */
	private int id;
	
	/**
	 * 文章标题
	 */
	private String title;
	
	/**
	 * 文章内容简介
	 */
	private String intro;
	
	/**
	 * 文章内容
	 */
	private String content;
	
	/**
	 * 文章作者ID，关联后台用户
	 */
	private int authorId;
	
	/**
	 * 文章创建时间
	 */
	private Date createTime;
	
	/**
	 * 文章最后修改时间
	 */
	private Date lastModifyTime;
	
	/**
	 * 文章类型，对应文章分类， 比如技术类，前端类，java，CSS，JavaScript等分类
	 */
	private int articleType;

	/**
	 * 标签
	 */
	private List<Tag> tags;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public int getArticleType() {
		return articleType;
	}

	public void setArticleType(int articleType) {
		this.articleType = articleType;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	
}
