package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Leave;
import domain.User;
import service.UserException;
import service.UserService;


@WebServlet("/QingjiaServlet")
public class QingjiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 依赖service
		UserService userService = new UserService();
		/**
		 * 实例化请假人
		 */
	    Leave qingjia =new Leave();
	    /**
	     * 获得用户初始信息
	     */
		User a=(User) request.getSession().getAttribute("session_user");
		/**
		 * 测试
		 */
		System.out.println("QingjiaServlet"+a.toString());
		try {
			/**
			 * 封装请假信息
			 */
			qingjia.setLeaveId(request.getParameter("leaveId"));
			qingjia.setEmployeeId(a.getId());
			qingjia.setEmployeeName(request.getParameter("employeeName"));
			qingjia.setStartTime(request.getParameter("startTime"));
			qingjia.setEndTime(request.getParameter("endTime"));
			qingjia.setDay(request.getParameter("day"));
			qingjia.setReason(request.getParameter("reason"));
			qingjia.setApprovePerson(request.getParameter("approvePerson"));
			qingjia.setType(a.getType());
			
			 System.out.println("已获得数据"+qingjia.toString());
			 
		/**	
		 * 执行此句插入记录
		 */
		userService.addLeave(qingjia);
		/**
		 * 重定向到空白页,不能获取request的信息
		 * 执行到此即操作成功
		 */
		response.sendRedirect(request.getContextPath() + "/null.jsp");
				} catch (UserException e) {
					/*
					 * 执行到这里，说明登录失败
					 * 1. 保存异常信息、form，到reuqest域
					 * 2. 转发到login.jsp
					 */
					request.setAttribute("msg", "请假失败"+e.getMessage());
				    request.setAttribute("form", qingjia);
					request.getRequestDispatcher("message.jsp").forward(request, response);
					System.out.println(qingjia.toString());
					return;
		}
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
