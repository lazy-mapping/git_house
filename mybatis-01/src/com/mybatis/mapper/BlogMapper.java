package com.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.mybatis.pojo.Blog;



public interface BlogMapper {
	//嵌套查询blog信息列表
	List<Blog> selectBlogList();
	
	//嵌套查询
	Blog selectBlogById(Integer id);
	
	Blog selectBlog(Integer id);
	
	List<Blog> selectBlogByTitle(String title);
	
	List<Blog> selectBlogBySort(String column);

	//List<Blog> selectBlogByPage(int offset, int pagesize);
	//List<Blog> selectBlogByPage(@Param(value="offset") int offset,@Param(value="pagesize") int pagesize);
	List<Blog> selectBlogByPage(Map<String, Object>map);
	 
	int insertBlog(Blog blog);
	
	int updateBlog(Blog blog);
	
	int deleteBlogById(Integer id);
	/*动态sql查询*/
	List<Blog> selectActiveBlogByTitle(String title);
	
	List<Blog> selectActiveBlogByTitleOrStyle(Blog blog);

	List<Blog> selectBlogByCondition(Blog blog);

	int updateBlogByCondition(Blog blog);

	List<Blog> selectBlogByConditionTrim(Blog blog);

	int updateBlogByConditionTrim(Blog blog);
	
	int deleteBlogList(List<Integer> ids);
	
	//嵌套查询结果
	List<Blog> selectBlogListNested();
	
	Blog selectBlogByIdConstructor(Integer id);
}
