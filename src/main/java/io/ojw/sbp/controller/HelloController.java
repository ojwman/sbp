package io.ojw.sbp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.ojw.sbp.domain.User;
import io.ojw.sbp.mapper.UserMapper;

@RestController
public class HelloController {
	@Autowired
	private UserMapper mapper;
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello 스프링부트!!";
	}
	
	@RequestMapping(value = "/helloUser/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> helloJson(@PathVariable String id) {
		try {
			User user = mapper.getLoginInfo(id);
//			System.out.println("user.toString(): " + user.toString());
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
