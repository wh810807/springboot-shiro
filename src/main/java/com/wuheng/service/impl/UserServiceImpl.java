package com.wuheng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuheng.dao.UserDao;
import com.wuheng.pojo.User;
import com.wuheng.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User findByName(String name) {
		return userDao.findByName(name);
	}

	@Override
	public User findById(Integer id) {
		return userDao.findById(id);
	}

}
