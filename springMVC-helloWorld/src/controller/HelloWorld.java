package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @RequestMapping的位置可以加在类名和方法名之前，最终路径为两者组合。 记得路径前要写“ / ”
 * @RequestMapping请求方式：(value="/helloworld".method=RequstMethod.POST),
  *            不指定method可以接受任何类型的请求；如果请求方式不对，会报告405错误。
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
	
	@RequestMapping(value = "/loginform",method = RequestMethod.GET)
	public String loginform(){
		//打开一个新增页面
		System.out.println("准备登录");
		return "/login";
	}
	//注解参数形式的匹配有两种：1.自动匹配  2.表单和controller的参数不一样，用@RequestParam
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginForm(@RequestParam(value = "realname") String username, String password){
		//执行插入操作
		System.out.println("姓名："+username+"   密码："+password);
		return "redirect:helloworld";
	}
	
	@RequestMapping("/testcookie")
	public String testCookie(@CookieValue("JSESSIONID") String sessionId) {
		//测试cookie
		System.out.println(sessionId);
		return "success";
	}
	
	@RequestMapping("/testheader")
	public String testHeader(@RequestHeader(value="User-Agent") String header) {
		//测试:请求头的一个参数
		System.out.println(header);
		return "success";
	}
	
	/***
	  * 通过表达式精确映射请求
	 * params 和 header 表示请求支持简单表达式
	 * param1 必须包含这此参数
	 * !param1 不能包含此参数 
	 * param1!=value1   必须包含这此参数,但值不为value1
	 * {"param1=value1","param2"} 必须包含两个参数，且param1值不为value1
	 */
	
	@RequestMapping(value="/testparam",params= {"username","age!=10"},headers= {"Accept-Language=zh-CN,zh;q=0.8"})
	public String testParam(String username, Integer age){
		System.out.println(username+" "+age);
		return "success";
	}
	/**
	 * ant风格的路径
	 * ? 匹配任意一个字符,   ??两个字符
	 * * 匹配任意字符
	 * ** 匹配多层路径
	 */
	@RequestMapping("/testantpath/*/a")
	public String testAntpath() {
		//测试:请求头的一个参数
		System.out.println("ant风格，匹配任意字符");
		return "success";
	}
}