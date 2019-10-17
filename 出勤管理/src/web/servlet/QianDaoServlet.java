package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import domain.Word;
import service.UserException;
import service.UserService;

@WebServlet("/QianDaoServlet")
public class QianDaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		// 依赖service,执行业务
		UserService userService = new UserService();
		/**
		 * 确定当前用户的：type，ID,用于实现发口令 或者 签口令。
		 */
		Word word = new Word();
		User a=(User) request.getSession().getAttribute("session_user");
		System.out.println("二级跳转："+request.getSession().getAttribute("session_type"));
		System.out.println(request.getSession().getAttribute("session_user"));
		try { 
			/**
			 * 封装用户id，type
			 */
			word.setId(a.getId());
			word.setType(a.getType());
			//wo
			/**
			 * 执行上课签到服务
			 */
			Word b = userService.qiandao(word);
				/**
				 * 保存口令到session对象中
				 */
			request.getSession().setAttribute("session_kouling", b.getWord());
			System.out.println("签到成功");
			response.sendRedirect(request.getContextPath() + "/kanword.jsp");
		} catch (UserException e) {
			System.out.println("签到失败");
			/**
			 * 保存失败信息
			 */
			request.setAttribute("msg", "签到失败!"+e.getMessage());
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} 
		
	}
}
