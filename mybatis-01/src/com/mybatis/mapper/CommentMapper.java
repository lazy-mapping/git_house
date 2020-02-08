package com.mybatis.mapper;

import java.util.List;
import com.mybatis.pojo.Comment;

public interface CommentMapper {
	
	List<Comment> selectCommentPostById(Integer postId);
	
}