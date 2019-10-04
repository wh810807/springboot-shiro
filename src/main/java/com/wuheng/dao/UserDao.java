package com.wuheng.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wuheng.pojo.User;

@Mapper
public interface UserDao {
	
	public User findByName(String name);
	
	public User findById(Integer id);

}
