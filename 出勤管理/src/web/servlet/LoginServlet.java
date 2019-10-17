package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.itcast.commons.CommonUtils;
import domain.User;
import service.UserException;
import service.UserService;

/**
 * 处理登录请求
 * 它是web层，所有与web相关的内容可以出现在这里！
 * 例如；request、repsonse、ServletContext，它们只能出现在web层！
 *
 */

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		// 依赖service
		UserService userService = new UserService();
		
		/*
		 * 1. 一句封装
		 * 把Map转换成指定类型的Bean对象。通常用来获取表单数据（request.getParameterMap()）封装到JavaBean中
		 */
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		/*
		 * 2. 获取身份
		 */
		String type=request.getParameter("type");
		/*
		 * 3. 调用userService的login()方法，完成业务
		 */
		try {
			User user = userService.login(form);
			user.setType(type);
			/*
			 * 执行到这里，说明登录成功了
			 */
			// 保存当前用户到session中
			request.getSession().setAttribute("session_user", user);
			request.getSession().setAttribute("session_type", type);
			//转发到相应的用户页面
			if(type.equals("teacher")) {
				System.out.println("已获得,教师登录");
			request.getRequestDispatcher("index_t.jsp").forward(request, response);
			}
			if(type.equals("student")) {
				System.out.println("已获得，学生登录");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			//输出测试
			System.out.println(request.getSession().getAttribute("session_user"));
			System.out.println("一级跳转页面："+request.getSession().getAttribute("session_type"));
		} catch (UserException e) {
			/*
			 * 执行到这里，说明登录失败
			 * 1. 保存异常信息、form，到reuqest域
			 * 2. 转发到login.jsp
			 */
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
}
	
