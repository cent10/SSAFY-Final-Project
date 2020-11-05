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
	
	// 유저 권한 부여 ('2:USER'로 부여)
	public int authorizeUser(int user);
	
	// 유저 권한 코드 조회 (1:ADMIN, 2:USER, 3:SELLER)
	public int readUserAuthority(int user);
}
