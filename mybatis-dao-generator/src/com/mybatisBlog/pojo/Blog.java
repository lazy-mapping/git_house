package com.mybatisBlog.pojo;

import java.io.Serializable;

public class Blog implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2076615184475043676L;

	private Integer id;

    private String title;

	 private Integer authorid; 

    private String state;

    private String style;

    private Boolean featured;

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
        this.title = title == null ? null : title.trim();
    }

 

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

	public Integer getAuthorid() {
		return authorid;
	}

	public void setAuthorid(Integer authorid) {
		this.authorid = authorid;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", authorid=" + authorid + ", state=" + state + ", style="
				+ style + ", featured=" + featured + "]";
	}



}