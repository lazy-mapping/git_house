package com.mybatis.vo;

import java.io.Serializable;

import com.mybatis.pojo.Blog;

public class BlogCustom extends Blog  implements Serializable{
	/**
	 *此处是扩展 ,给数据库加了一列 authorUsername
	 */
	private static final long serialVersionUID = 1L;
	private String authorUsername;

	public String getAuthorUsername() {
		return authorUsername;
	}

	public void setAuthorUsername(String authorUsername) {
		this.authorUsername = authorUsername;
	}

	@Override
	public String toString() {
		return "BlogCustom [authorUsername=" + authorUsername + ", toString()=" + super.toString() + "]";
	}
	
}