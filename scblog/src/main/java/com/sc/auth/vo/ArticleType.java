package com.sc.auth.vo;

public class ArticleType {
	
	/**
	 * 类型ID
	 */
	private int id;
	
	/**
	 * 类型名称
	 */
	private String typeName;
	
	/**
	 * 同类型的文章数量
	 */
	private int articleCount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
	
	
}
