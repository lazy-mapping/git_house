package elec.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import elec.domain.ElecUser;
/**
  *  自定义过滤器
 * @author 王少彬。。。。
 *
 */
public class SystemFilter implements Filter {
	
	//这些系统页面没有Session，但是需要访问的url，需要设置放行
	List<String> list = new ArrayList<String>();
	/**当服务启动的时候初始化*/
	public void init(FilterConfig config) throws ServletException {
		list.add("/index.jsp");
		list.add("/image.jsp");
		list.add("/system/elecMenuAction_menuHome.do");
		
		list.add("/error.jsp");
		list.add("/system/elecMenuAction_logout.do");
	}

	/**每次访问URL连接之前，都要访问的方法*/
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) 
					throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		//记住我的代码放置到过滤器中
		String path = request.getServletPath();
		this.forwordIndexPage(request,path);
		
		//如果当前访问的连接是定义list中存放的连接，此时需要放行
		if(list.contains(path)){
			chain.doFilter(request, response);
			return;
		}
		//存在Session（不为空）的时候要放行，Session为空的时候，定向到首页
		ElecUser elecUser = (ElecUser)request.getSession().getAttribute("globle_user");
		if(elecUser!=null){
				chain.doFilter(request, response);
				return;
		}
	//转发
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}
	
	

	/**销毁*/
	public void destroy() {
	}

	//【记住我】的代码放置到过滤器中，在跳转到index.jsp页面之前
	private void forwordIndexPage(HttpServletRequest request, String path) {
		//从cookie中拿数据
		if(path.equals("/index.jsp")){
			String name = "";
			String password = "";
			String checked = "";
			Cookie [] cookies = request.getCookies();
			if(cookies!=null && cookies.length>0){
				for(Cookie cookie:cookies){
					if(cookie.getName().equals("name")){
						name = cookie.getValue();
						//处理中文二进制解码问题
						try {
							name = URLDecoder.decode(name, "UTF-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						checked = "checked";
					}
					if(cookie.getName().equals("password")){
						password = cookie.getValue();
					}
				}
			}
			request.setAttribute("name", name);
			request.setAttribute("password", password);
			request.setAttribute("checked", checked);
		}
	}
	

}
