package com.mybatis.mapper;

import com.mybatis.pojo.Post;

public interface PostMapper {
	
	Post selectPostById(Integer id);
	
}