package com.mybatis.example;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.mybatis.util.MyBatisUtil;
import com.mybatisBlog.mapper.BlogMapper;
import com.mybatisBlog.pojo.Blog;
import com.mybatisBlog.pojo.BlogExample;

public class BlogExampleTest {
	
	
	    /**
	     *  测试 ByExample 的用法
	     *   创建blogCriteria对象，使用and方法
	     */
	    @Test
		public void selectBlogByExample() {
			SqlSession session = MyBatisUtil.getSqlSession();
			
			BlogMapper blogMapper= session.getMapper(BlogMapper.class);
			BlogExample blogExample = new BlogExample();
			BlogExample.Criteria blogCriteria = blogExample.createCriteria(); 
			blogCriteria.andTitleLike("%l%");
			
			List<Blog> blogList= blogMapper.selectByExample(blogExample);
			
			session.close();
			System.out.print(blogList);
		}
		
	    
	    /** 性能问题
		 * session级别，一级缓存默认存在。
		 * 在同一个session范围内执行相同的（查询）操作，第二次查询从缓存中获取数据。
		 */
	    //@Test
		public void selectBlogCacheLevelOne1() {
			SqlSession session = MyBatisUtil.getSqlSession();
			BlogMapper blogMapper= session.getMapper(BlogMapper.class);
			
			Blog blog1 = blogMapper.selectByPrimaryKey(1);
			System.out.print(blog1+"结果已查询\n");
			
			Blog blog2 = blogMapper.selectByPrimaryKey(1);
			System.out.print(blog2+"结果已查询：从缓存中获取数据\n");
			
			session.close();
			System.out.print("session 已关闭，仅执行一次sql查询");
		}
		
		/** 刷新缓存
		 * 在同一个session范围内执行两次相同的（查询）操作，中间有增删改操作，会刷新缓存
		 */
	    //@Test
		public void selectBlogCacheLevelOne2() {
			SqlSession session = MyBatisUtil.getSqlSession();
			BlogMapper blogMapper= session.getMapper(BlogMapper.class);
			
			Blog blog1 = blogMapper.selectByPrimaryKey(1);
			System.out.print("结果已查询\n");
			
			blog1.setFeatured(true);
			blogMapper.updateByPrimaryKey(blog1);
			System.out.println("执行更新操作，刷新缓存");
			
			Blog blog2 = blogMapper.selectByPrimaryKey(1);
			System.out.print(blog2+"重新执行查询\n");
			
			session.commit();
			session.close();
			System.out.print("session 已关闭，执行两次sql查询");
		}
	    
	    /** 
		 * 二级缓存默认不开启,不在同一个session范围内
		 * 开启条件：BlogMapper.xml添加<cache/>    Blog.java实现序列化
		 * 当 中间有 增删改操作，缓存区就清空。
		 */
	    //@Test
		public void selectBlogCacheLevelTwo1() {
			SqlSession session1 = MyBatisUtil.getSqlSession();
			BlogMapper blogMapper1= session1.getMapper(BlogMapper.class);
			Blog blog1 = blogMapper1.selectByPrimaryKey(1);
			System.out.print(blog1+"结果已查询\n");
			
		// 缓存刷新
		/**
		 *  blog1.setFeatured(true); blogMapper1.updateByPrimaryKey(blog1);
		 * System.out.println("执行更新操作，刷新缓存"); session1.commit();
		 */
			session1.close();
			
			SqlSession session2 = MyBatisUtil.getSqlSession();
			BlogMapper blogMapper2= session2.getMapper(BlogMapper.class);
			Blog blog2 = blogMapper2.selectByPrimaryKey(1);
			System.out.print(blog2+"重新执行查询？\n");
			session2.close();
			
			System.out.print("session 已关闭。关闭二级缓存，执行两次sql查询； 开启就执行一次。");
		}
}
