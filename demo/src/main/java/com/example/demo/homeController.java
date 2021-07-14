package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class homeController {
	@RequestMapping("/home")
	@ResponseBody
	
	// 요청 url : http://localhost:8070/home
	public String home() {
		return "안녕 스프링부트";
	}
}
