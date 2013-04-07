package com.sc.auth.vo;

import java.util.Date;

/**
 * 评论对象
 * @author stephen_chen
 *
 */
public class CommentVo {
	
	private int id;
	
	private String visitor;
	
	private String email;
	
	private String website;
	
	private String comment;
	
	private int articleId;
	
	private Date createTime;
	
	private int followedId;
	
	private int status;
	
		public static int STATUS_UNVERIFIED = 0;
		public static int STATUS_VERIFIED = 1;
		public static int STATUS_REJECTED = -1;
		

	public int getId() {
		return id;
	}

	public String getVisitor() {
		return visitor;
	}

	public void setVisitor(String visitor) {
		this.visitor = visitor;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getFollowedId() {
		return followedId;
	}

	public void setFollowedId(int followedId) {
		this.followedId = followedId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
