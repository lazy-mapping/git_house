package com.mybatis.pojo;

import java.sql.Date;
import java.util.List;

public class Post {

	//文章
	private Integer id;
	private Author author;
	private Blog blog;
	private Date  createOn;
	private String section;
	private String subject;
	private String draft;
	private String body;
	private Integer visit;
	private List<Tag> tagList;
	private List<Comment> commentList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public Date getCreateOn() {
		return createOn;
	}
	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDraft() {
		return draft;
	}
	public void setDraft(String draft) {
		this.draft = draft;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Integer getVisit() {
		return visit;
	}
	public void setVisit(Integer visit) {
		this.visit = visit;
	}
	public List<Tag> getTagList() {
		return tagList;
	}
	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}
	public List<Comment> getComments() {
		return commentList;
	}
	public void setComments(List<Comment> comments) {
		this.commentList = comments;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", author=" + author + ", blog=" + blog + ", createOn=" + createOn + ", section="
				+ section + ", subject=" + subject + ", draft=" + draft + ", body=" + body + ", visit=" + visit
				+ ", tagList=" + tagList + ", commentList=" + commentList + "]";
	}
 	
}
