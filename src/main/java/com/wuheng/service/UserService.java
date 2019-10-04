package com.wuheng.service;

import com.wuheng.pojo.User;

public interface UserService {

	public User findByName(String name);
	
	public User findById(Integer id);
	
}
