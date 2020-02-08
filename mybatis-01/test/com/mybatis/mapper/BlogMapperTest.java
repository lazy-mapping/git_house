package com.mybatis.mapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.mybatis.pojo.Author;
import com.mybatis.pojo.Blog;
import com.mybatis.util.MyBatisUtil;
import com.mybatis.vo.BlogCustom;

public class BlogMapperTest {
	
	/**嵌套查询,查blog表信息列表，同时要查author表
	 * 此时查blog 应执行sql 1+n次，若关联表有重复的author数据，只执行 1 次sql
	 * 默认一级缓存开启 的好处
	 */
	   //@Test
	   public void selectBlogList() {
	   SqlSession session = MyBatisUtil.getSqlSession();
					
		BlogMapper blogMapper= session.getMapper(BlogMapper.class);
		List<Blog> blogList= blogMapper.selectBlogList();
					
		session.close();
		System.out.print(blogList);
		}
	
	   
		/**
		 *  查author表
		 */
	   //@Test 
	   public void selectAuthorById() {
	   SqlSession session = MyBatisUtil.getSqlSession();
					
		AuthorMapper authorMapper= session.getMapper(AuthorMapper.class);
		Author author = authorMapper.selectAuthorById(1);
					
		session.close();
		System.out.print(author);
		}
	
	/**嵌套查询,查blog表，同时要查author表*/
	   //@Test
	   public void selectBlogById() {
	   SqlSession session = MyBatisUtil.getSqlSession();
					
		BlogMapper blogMapper= session.getMapper(BlogMapper.class);
		Blog blog= blogMapper.selectBlogById(1);
					
		session.close();
		System.out.print(blog);
		}
	   
	/** 用接口的查询id*/
	//@Test
	public void testSelectBlog() {
		SqlSession session = MyBatisUtil.getSqlSession();
		
		BlogMapper blogMapper= session.getMapper(BlogMapper.class);
		Blog blog = blogMapper.selectBlog(1);
		 
		session.close();
		System.out.print(blog);
	}
	
	/** 不用接口*/
	//@Test
	public 	void  testSeklectBlogNoInterface() {
		SqlSession session = MyBatisUtil.getSqlSession();
		
		
		Blog blog = session.selectOne("com.mybatis.mapper.BlogMapper.selectBlog",1);
		 
		session.close();
		System.out.print(blog);
	}
	
	/** 模糊查询，title部分*/
	//@Test
	public 	void  selectBlogByTitle() {
		SqlSession session = MyBatisUtil.getSqlSession();
		
		BlogMapper blogMapper= session.getMapper(BlogMapper.class);
		List<Blog> blogList = blogMapper.selectBlogByTitle("%Z%");
		
		session.close();
		System.out.print(blogList);
	}
	
	/** 排序，title部分*/
	//@Test
	public 	void  selectBlogBySort() {
		SqlSession session = MyBatisUtil.getSqlSession();
		
		BlogMapper blogMapper= session.getMapper(BlogMapper.class);
		List<Blog> blogList = blogMapper.selectBlogBySort("title");
		
		session.close();
		System.out.print(blogList);
	}
	
	/** 分页*/
	//@Test
	public 	void  selectBlogByPage() {
		SqlSession session = MyBatisUtil.getSqlSession();
		
		BlogMapper blogMapper= session.getMapper(BlogMapper.class);
		/* List<Blog> blogList = blogMapper.selectBlogByPage(2,2); */
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("offset", 2);
		map.put("pagesize", 2);
		List<Blog> blogList = blogMapper.selectBlogByPage(map);
		session.close();
		System.out.print(blogList);
	}
	
	/** 插入*/
	//@Test
	public void testInsertBlog() {
		SqlSession session = MyBatisUtil.getSqlSession();
		
		BlogMapper blogMapper= session.getMapper(BlogMapper.class);
		
		Blog blog = new Blog();
	    int  count = blogMapper.insertBlog(blog);
		 
		session.commit();
		session.close();
		System.out.print(blog);
		System.out.print("插入了"+count+"条记录");
	}
	
	/** 更新*/
	//@Test
	public void testUpdateBlog() {
		SqlSession session = MyBatisUtil.getSqlSession();
		
		BlogMapper blogMapper= session.getMapper(BlogMapper.class);
		//若此处属性更新一部分，剩余则按默认值保存，即blog默认的构造方法
		Blog blog = new Blog();
	    blog.setId(1);
	    blog.setTitle("我的Blog");
	    blog.setStyle("black");
	    blog.setState("ACTIVE");
	    blog.setFeatured(false);
	   //blog.setAuthorId(2);
	    int count = blogMapper.updateBlog(blog);
		session.commit();
		session.close();
		System.out.print(blog);
		System.out.print("修改了"+count+"条记录");
	}
	
	/** 按id删除*/
	//@Test
	public void testDeleteBlogById() {
		SqlSession session = MyBatisUtil.getSqlSession();
		
		BlogMapper blogMapper= session.getMapper(BlogMapper.class);
		
	    int count = blogMapper.deleteBlogById(100);
		session.commit();
		session.close();

		System.out.print("删除了"+count+"条记录");
	}
	
	/** 动态查询--输入title字符串查询& 默认状态为active*/
	//@Test
	public void selectActiveBlogByTitle() {
		SqlSession session = MyBatisUtil.getSqlSession();
		
		BlogMapper blogMapper= session.getMapper(BlogMapper.class);
		
		//List<Blog> blogList= blogMapper.selectActiveBlogByTitle("o");
		List<Blog> blogList= blogMapper.selectActiveBlogByTitle(null);
		
		session.close();
		System.out.print(blogList);
	}
	
	/** 动态查询--1.状态激活 & 2.检索title、style关键字 or featured关键字*/
	//@Test
	public void selectActiveBlogByTitleOrStyle() {
		SqlSession session = MyBatisUtil.getSqlSession();
		
		BlogMapper blogMapper= session.getMapper(BlogMapper.class);
		Blog blog = new Blog();
		blog.setTitle("%o%");
		blog.setStyle("black");
		List<Blog> blogList= blogMapper.selectActiveBlogByTitleOrStyle(blog);
		
		session.close();
		System.out.print(blogList);
	}
	
	/** 自动修补查询条件--是否检查状态为active ，title字符串，featured推荐状态*/
	//@Test
	public void selectBlogByCondition() {
		SqlSession session = MyBatisUtil.getSqlSession();
		
		BlogMapper blogMapper= session.getMapper(BlogMapper.class);
		Blog blog = new Blog();
		
		 blog.setState("ACTIVE"); blog.setTitle("%o%");  blog.setFeatured(false);
		 
		List<Blog> blogList= blogMapper.selectBlogByCondition(blog);
		
		session.close();
		System.out.print(blogList);
	}
	
	/** 按需求更新 by id*/
	//@Test
	public void testUpdateBlogByCondition() {
		SqlSession session = MyBatisUtil.getSqlSession();
		
		BlogMapper blogMapper= session.getMapper(BlogMapper.class);
		//若此处属性更新一部分，剩余则按默认值保存，即blog默认的构造方法,我已经将其注释掉
		Blog blog = new Blog();
	    blog.setId(2);
	    
	    blog.setTitle("我的Blogggggg");
	    //blog.setStyle("black");
	    //blog.setState("ACTIVE");
	    //blog.setFeatured(false);
	   // blog.setAuthorId(2);
	    int count = blogMapper.updateBlogByCondition(blog);
		
	    session.commit();
		session.close();
		
		System.out.print(blog);
		System.out.print("修改了"+count+"条记录");
	}
	
	/** trim(修剪的意思)版本 
	 * 自动修补查询条件--是否检查状态为NOT ACTIVE ，title字符串，featured推荐状态*/
	//@Test
	public void selectBlogByConditionTrim() {
		SqlSession session = MyBatisUtil.getSqlSession();
		
		BlogMapper blogMapper= session.getMapper(BlogMapper.class);
		Blog blog = new Blog();
		
		 blog.setState("NOT ACTIVE"); 
		 //blog.setTitle("%o%");  blog.setFeatured(false);
		 
		List<Blog> blogList= blogMapper.selectBlogByConditionTrim(blog);
		
		session.close();
		System.out.print(blogList);
	}
	
	/** trim 版本
	 * 按需求更新 by id*/
	//@Test
	public void testUpdateBlogByConditionTrim() {
		SqlSession session = MyBatisUtil.getSqlSession();
		
		BlogMapper blogMapper= session.getMapper(BlogMapper.class);
		//若此处属性更新一部分，剩余则按默认值保存，即blog默认的构造方法,我已经将其注释掉
		Blog blog = new Blog();
	    blog.setId(4);
	    
	    //blog.setTitle("我的Blogggggg");
	    //blog.setStyle("black");
	    blog.setState("ACTIVE");
	    //blog.setFeatured(false);
	   // blog.setAuthorId(2);
	    int count = blogMapper.updateBlogByConditionTrim(blog);
		
	    session.commit();
		session.close();
		
		System.out.print(blog);
		System.out.print("修改了"+count+"条记录");
	}
	
	/**  批量删除*/
	//@Test
	public void deleteBlogList() {
		SqlSession session = MyBatisUtil.getSqlSession();
		
		BlogMapper blogMapper= session.getMapper(BlogMapper.class);
		List<Integer> ids =Arrays.asList(104,105,106);
	    
	    int count = blogMapper.deleteBlogList(ids);
		
	    session.commit();
		session.close();

		System.out.print("修改了"+count+"条记录");
	}
	
	/** 嵌套查询结果 */
	//@Test
	public 	void  TestSelectBlogListNested() {
		SqlSession session = MyBatisUtil.getSqlSession();
		
		BlogMapper blogMapper= session.getMapper(BlogMapper.class);
		List<Blog> blogList = blogMapper.selectBlogListNested();
		
		session.close();
		System.out.print(blogList);
	}
	
	/** 扩展blog 查询 */
	//@Test
	public 	void  selectBlogVoById() {
		SqlSession session = MyBatisUtil.getSqlSession();
		BlogMapperCustom blogMapperCustom= session.getMapper(BlogMapperCustom.class);

		BlogCustom blogCustom = blogMapperCustom.selectBlogById(1);
		
		session.close();
		System.out.print(blogCustom);
	}
	
	/** 构造方法调用， 查询 */
	//@Test
	public 	void  selectBlogByIdConstructor() {
		SqlSession session = MyBatisUtil.getSqlSession();
		BlogMapper blogMapper= session.getMapper(BlogMapper.class);

		Blog blog = blogMapper.selectBlogByIdConstructor(1);
		
		session.close();
		System.out.print(blog);
	}
	
	/** 
	 *  测试延迟加载 ,性能问题
	 *  在非延迟加载下：会将数据全部查出，不论是否要查blog的任意属性--都要查author，速度很慢
	 *  配置延迟加载：全局配置，添加代理包asm-3.3.1.jar,cglb-2.2.2.jar。
	 *  积极的(默认)：不查blog的任意属性--就不查author     只要查blog的任意属性--都要查关联的author
	 *  不积极(配置)：只访问blog的非author属性，就不查关联的author，性能最优
	 */
	@Test
	public void testSelectBlogByIdLazyLoading() {
		SqlSession session = MyBatisUtil.getSqlSession();
		BlogMapper blogMapper= session.getMapper(BlogMapper.class);
		
		System.out.println("开始查询Blog");
		Blog blog = blogMapper.selectBlogById(1);
		session.close();
		
		System.out.println("查询blog的title属性"+"=");
		System.out.println(blog.getTitle());
		//System.out.println("查询author属性的username"+"=");
		//System.out.println(blog.getAuthor().getUsername());
		System.out.print("查询结束");
	}
}








