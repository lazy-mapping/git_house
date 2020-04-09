package elec.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class LogonUtils {

	/**验证码*/
	public static boolean checkNumber(HttpServletRequest request) {
		//从页面上获取验证码
		String checkNumber = request.getParameter("checkNumber");
		if(StringUtils.isBlank(checkNumber)){
			return false;
		}
		//从Session中获取验证码
		String CHECK_NUMBER_KEY = (String)request.getSession().getAttribute("CHECK_NUMBER_KEY");
		if(StringUtils.isBlank(CHECK_NUMBER_KEY)){
			return false;
		}
		//比较字符串并忽略大小写
		return checkNumber.equalsIgnoreCase(CHECK_NUMBER_KEY);
	}

	
	
	/**记住我*/
	public static void rememberMe(HttpServletRequest request,
			HttpServletResponse response, String name, String password) {
		//创建2个Cookie
		//解决中文名称不能放置到cookie中,二进制编码
		try {
			name = URLEncoder.encode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Cookie nameCookie = new Cookie("name", name);
		Cookie passwordCookie = new Cookie("password",password);
		//设置Cookie存在的有效路径（绝对路径）
		nameCookie.setPath(request.getContextPath()+"/");
		passwordCookie.setPath(request.getContextPath()+"/");
		//设置Cookie存在的有效时间（周期1周）
		String remeberMe = request.getParameter("remeberMe");
		//设置有效时间为一周
		if(remeberMe!=null && remeberMe.equals("yes")){
			nameCookie.setMaxAge(60*60*24*7);
			passwordCookie.setMaxAge(60*60*24*7);
		}
		//取消有效时间
		else{
			nameCookie.setMaxAge(0);
			passwordCookie.setMaxAge(0);
		}
		//存放cookie
		response.addCookie(nameCookie);
		response.addCookie(passwordCookie);
	}
}
