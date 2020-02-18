package io.ojw.sbp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.ojw.sbp.domain.User;
import io.ojw.sbp.mapper.UserMapper;

@Controller
public class UserController {
	@Autowired
	private UserMapper mapper;
	
	@RequestMapping(value = "/korean", method = RequestMethod.POST)
	public String korean(@RequestParam String name, Model model) throws Exception {
		System.out.println("name=" + name);
		model.addAttribute("name", name);
		return "hello2";
	}
	
	@RequestMapping("/hello2")
	public void hello2(Model model) throws Exception {
		String id = "ojw";
		model.addAttribute("name", mapper.getName(id));
		
		User user = mapper.getLoginInfo(id);
		model.addAttribute("user", user);
		
		List<User> users = mapper.getUsers();
		model.addAttribute("users", users);
	}
}
