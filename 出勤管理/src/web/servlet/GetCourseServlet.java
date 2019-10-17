package web.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Course;
import domain.User;
import service.UserException;
import service.UserService;

@WebServlet("/GetCourseServlet")
public class GetCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public GetCourseServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 依赖service
		UserService userService = new UserService();
				/**
				 * 实例化用户
				 */
		User user =new User();
		//CourseTimeMap timeMap= new CourseTimeMap();
		Course course= new Course();
		//ArrayList<CourseTimeMap> list=new ArrayList<CourseTimeMap>();
		HashMap<String ,String > hashmap=new HashMap<String ,String >();
			    /**
			     * 获得用户初始信息
			     */
		User a=(User) request.getSession().getAttribute("session_user");
		String id =a.getId(); 
				/**
				 * 测试
				 */
		System.out.println("KebiaoServlet"+a.toString());
		try {
			/**
			 * 调用相应的userService的方法，收集数据
			*/
			hashmap=userService.getCourseTimemap();
		    /**
			  * 封装请假信息
			 */
			course.setCourseid("");
			System.out.println("调用service方法"+id);

		    request.getRequestDispatcher("kebiao.jsp").forward(request, response);
		    request.getSession().setAttribute("session_kebiao", hashmap);
		           
			/**
			 * 显示数据成表格
			 */
			System.out.println("已获得数据");
			System.out.println(hashmap);
			System.out.println(hashmap.size());
				/**
				 * 执行到此即操作已经成功
				 */
			} catch (UserException e) {
					/*
					* 执行到这里，说明登录失败
					* 1. 保存异常信息、form，到reuqest域
					* 2. 转发到login.jsp
					*/
					request.setAttribute("msg", "查看出勤记录失败"+e.getMessage());
					request.getRequestDispatcher("message.jsp").forward(request, response);
					System.out.println(user.toString());
					return;
				}	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
