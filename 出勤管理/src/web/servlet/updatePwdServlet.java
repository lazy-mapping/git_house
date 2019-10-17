package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.UserException;
import service.UserService;

@WebServlet("/updatePwdServlet")
public class updatePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 依赖service
		UserService userService = new UserService();
		User user= new User();
		User a=(User) request.getSession().getAttribute("session_user");
		try {
			user.setId(a.getId());
			user.setType(a.getType());
			user.setPassword(a.getPassword());
			user.setPwd(request.getParameter("pwd"));
			user.setNewPassword(request.getParameter("newpassword"));
			user.setEnsurePassword(request.getParameter("ensurepassword"));	
		/**	
		 * 执行此句更新密码
		 */
	     System.out.println(user.toString());	
		 userService.updatePwd(user);
		 System.out.println("修改密码成功");
			//转发到相应的用户页面
		 request.getRequestDispatcher("null.jsp").forward(request, response);
				} catch (UserException e) {
					/*
					 * 执行到这里，说明登录失败
					 * 1. 保存异常信息、form，到reuqest域
					 * 2. 转发到login.jsp
					 */
					request.setAttribute("msg", e.getMessage());
					request.getRequestDispatcher("/message.jsp").forward(request, response);
					System.out.println(user.toString());
					return;
		}
	}

}
