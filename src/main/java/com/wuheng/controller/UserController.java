package com.wuheng.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		System.out.println("UserController.hello()");
		return "ok";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "user/add";
	}
	
	@RequestMapping("/update")
	public String update() {
		return "user/update";
	}
	
	@RequestMapping("/toLogin")
	public String login() {
		return "user/login";
	}
	
	@RequestMapping("/noAuth")
	public String noAuth() {
		return "user/noAuth";
	}
	
	//测试thymeleaf
	@RequestMapping("/thymeleaf")
	public String testThymeleaf(Model model) {
		model.addAttribute("name", "吴恒");
		return "test";
	}
	
	//登录逻辑处理
	@RequestMapping("/doLogin")
	public String login(String name,String password,Model model) {
		System.out.println("name:"+name);
		System.out.println("password:"+password);
		
		//使用Shiro编写认证操作
		
		//1、获取Subject
		Subject subject = SecurityUtils.getSubject();
		
		//2、封装用户数据
		UsernamePasswordToken token = new UsernamePasswordToken(name,password);
		
		//3、执行登录的方法
		try {
			subject.login(token);
			return "redirect:/thymeleaf";
		} catch (UnknownAccountException e) {
			model.addAttribute("msg","用户名错误");
			return "user/login";
		}catch (IncorrectCredentialsException e) {
			model.addAttribute("msg","密码错误");
			return "user/login";
		}	
	}

}
