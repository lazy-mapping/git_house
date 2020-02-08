package com.mybatis.pojo;

public class Blog {
	//有参构造函数
	public Blog(Integer id, String title) {
		super();
		this.id = id;
		this.title = title;
		System.out.println("构造方法调用");
	}
	
	private Integer id;
	private String title;
	/* private int authorId; */
	private Author author;
	private String state;
	private Boolean featured;
	private String style;
	
	public Blog() {
		super();
		/*
		 * this.title = "未命名"; this.authorId = 1; this.state = "NOT ACTIVE";
		 * this.featured = false; this.style = "red";
		 */
	}
	
	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", author=" + author + ", state=" + state + ", featured="
				+ featured + ", style=" + style + "]\n";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Boolean getFeatured() {
		return featured;
	}
	public void setFeatured(Boolean featured) {
		this.featured = featured;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
}
