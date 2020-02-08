package com.mybatis.pojo;

import java.sql.Date;

public class Comment {

	//评论
	private Integer id;
	private String name;
	private String comment;
	private Date  createOn;
	private Post post;
	private Author author;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreateOn() {
		return createOn;
	}
	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", comment=" + comment + ", createOn=" + createOn + ", post="
				+ post + ", author=" + author + "]";
	}
	
 	
}
