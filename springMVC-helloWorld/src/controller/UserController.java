package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	
	/***
	 * restfule风格的url请求方式 ：
	 * 		GET获取  POST新建  PUT更新  DELETE删除
	 * 注解参数形式的匹配：
	 * 		url中地址携带参数，使用@RequestParam；默认值defaultValue；required(默认true) 设置是否可选参数
	 * 基本类型参数必须使用包装类类型。当参数不存在，springmvc会将包装类转换成null，否则会异常
	 */

	@RequestMapping(value = "/users",method = RequestMethod.GET)
	public String list(@RequestParam(value = "currentpage",required = false,defaultValue = "1") Integer currentpage, 
			           @RequestParam(value = "pagesize",defaultValue = "10")Integer pagesize){
		//打开列表页
		System.out.println("currentpage:" + currentpage);
		System.out.println("pagesize:" + pagesize);
		return "list";
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public String edit(@PathVariable(value="id") Integer id){
		//打开回显页
		System.out.println("id:" + id);
		return "actingIndex";
	}
	
	@RequestMapping(value="/user1/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable(value="id") Integer id){
		//执行删除
		System.out.println("id:" + id);
		return "edit";
	}
	
	@RequestMapping(value="/user2/{id}", method=RequestMethod.PUT)
	public String update(@PathVariable(value="id") Integer id){
		//执行更新
		System.out.println("id:" + id);
		return "edit";
	}
}
