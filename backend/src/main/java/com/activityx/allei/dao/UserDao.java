package com.activityx.allei.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.activityx.allei.dto.User;

// CRUD
@Mapper
public interface UserDao {
	// create
	public int create(User user);

	// read
	public List<User> readAll();

	public User findById(int id);

	public User findByName(String name);

	public List<User> findlistByName(String name);

	public User findByEmail(String email);

	public List<User> findlistByEmail(String email);

	public User findByUkey(long ukey);

	// update
	public int update(User user);

	// delete
	public int delete(int id);
}
