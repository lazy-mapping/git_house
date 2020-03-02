package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @RequestMapping的位置可以加在类名和方法名之前，最终路径为两者组合。 记得路径前要写“ / ”
 * @RequestMapping请求方式：(value="/helloworld".method=RequstMethod.POST),
  *            不指定method可以接受任何类型的请求；如果请求方式不对，会报告405错误。
 * @author 王少彬。。。。
 * 
 */
@Controller
public class HelloWorld {
	
	@RequestMapping(value = "/helloworld",method = RequestMethod.GET)
	public String helloworld(){
		//打开一个新增页面
		System.out.println("经过这个方法：helloworld");
		return "/helloworld";
	}
	
	//注解参数形式的匹配有两种：1.自动匹配  2.表单和controller的参数不一样，用@RequestParam
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginForm(@RequestParam(value = "realname") String username, String password){
		//执行插入操作
		System.out.println("姓名："+username+"   密码："+password);
		System.out.println("登录成功");
		return "redirect:helloworld";
	}
	
	
}