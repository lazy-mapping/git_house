package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
	
	//注解参数形式的匹配： url中地址携带参数，使用@RequestParam；默认值defaultValue
	//建议基本类型参数(必须)使用包装类类型。当参数不存在，springmvc会将包装类转换成null，否则会异常
	//可以使用required(默认true) 设置是否可选参数
	@RequestMapping("/list")
	public String list(@RequestParam(value = "currentpage",required = false,defaultValue = "1") Integer currentpage, 
			           @RequestParam(value = "pagesize",defaultValue = "10")Integer pagesize){
		System.out.println("currentpage:" + currentpage);
		System.out.println("pagesize:" + pagesize);
		return "list";
	}
	
	@RequestMapping("/get/{id}")
	//restfule风格的url请求方式
	//@RequestMapping(value="/get/{id}", method=RequestMethod.GET)
	public String get(@PathVariable(value="id") Integer id){
		System.out.println("id:" + id);
		return "edit";
	}
}
