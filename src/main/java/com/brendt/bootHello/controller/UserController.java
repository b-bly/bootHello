package com.brendt.bootHello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brendt.bootHello.dao.RoleDao;
import com.brendt.bootHello.dao.UserDao;
import com.brendt.bootHello.model.User;


@RestController
@RequestMapping("/api")
public class UserController {
	  @Autowired
	    UserDao userDao;

	    @Autowired
	    RoleDao roleDao;

//	    @Autowired
//	    PasswordEncoder passwordEncoder;

	    
	    // example: receives /users/john
	    @GetMapping("/users/{username}")
	    public void getUserProfile(@PathVariable(value = "username") String username) {
	    	System.out.println("path var: " + username);
	        User user = userDao.findByUsername(username);
	        System.out.println("Auth controller, user: " + user);
	    }
	    
}
