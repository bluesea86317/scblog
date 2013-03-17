package com.sc.auth.vo;

import java.util.Date;

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
}
