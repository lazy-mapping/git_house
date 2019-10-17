package web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Auth;
import domain.Record;
import domain.User;
import service.UserException;
import service.UserService;

@WebServlet("/ChuQinServlet")
public class ChuQinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChuQinServlet() {
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
		Record record =new Record();
		ArrayList<Record> list=null;
			    /**
			     * 获得用户初始信息
			     */
		User a=(User) request.getSession().getAttribute("session_user");
		String id =a.getId(); 
				/**
				 * 测试
				 */
		System.out.println("ChuQinServlet"+a.toString());
		
				try {
					/**
					 * 识别身份，封装请假信息
					 */
			record.setId(id);		
			/**
			 * 判定是否教师身份，调用相应的userService的方法，完成查看
			 */
			boolean b=Auth.Shenfen(id);
			if(b) {
				System.out.println("调用service方法教师"+id);
		           list=userService.selectRecordTeacher(record);
		           request.getRequestDispatcher("gerenChuqinT.jsp?countt="+list.size()).forward(request, response);
		           request.getSession().setAttribute("session_list", list);
		           
			}
			if(!b){
				System.out.println("调用service方法学生"+id);
				 list=userService.selectRecordStudent(record);
				 //String aString=userService.selectRecordstudent(record);
			     System.out.println(list);
				 request.getRequestDispatcher("gerenChuqinS.jsp?counts="+list.size()).forward(request, response);
				 //request.getSession().setAttribute("session_lists", aString);
				 request.getSession().setAttribute("session_lists", list);
			}
			/**
			 * 显示数据成表格
			 */
			System.out.println("已获得数据");
			System.out.println(list);
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
