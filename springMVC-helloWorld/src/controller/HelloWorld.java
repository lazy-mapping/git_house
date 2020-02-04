package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {
	
	@RequestMapping("/helloworld")
	public String helloworld(){
		System.out.println("经过这个方法：helloworld");
		return "/helloworld";
	}
}