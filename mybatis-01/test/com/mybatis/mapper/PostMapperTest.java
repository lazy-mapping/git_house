package com.mybatis.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.mybatis.pojo.Post;
import com.mybatis.util.MyBatisUtil;

public class PostMapperTest {
	

	   @Test
	   public void testSelectOPost() {
	   SqlSession session = MyBatisUtil.getSqlSession();
					
		PostMapper postMapper= session.getMapper(PostMapper.class);
		Post post= postMapper.selectPostById(1);
					
		session.close();
		System.out.print(post);
		}	

}








