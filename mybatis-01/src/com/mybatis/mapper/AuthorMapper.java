package com.mybatis.mapper;

import com.mybatis.pojo.Author;

public interface AuthorMapper {
	//嵌套查询
	Author selectAuthorById(Integer id);
	
}