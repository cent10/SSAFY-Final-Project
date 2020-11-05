package com.activityx.allei.service;

import java.util.List;

import com.activityx.allei.dto.User;

public interface UserService {
	// 회원가입
	boolean joinUser(User user);

	// 회원탈퇴
	boolean deleteUser(int id);

	// 회원검색
	List<User> findAll();

	User findById(int id);

	User findByName(String name);

	List<User> findlistByName(String name);

	User findByEmail(String email);

	List<User> findlistByEmail(String email);

	User findByUkey(long ukey);

	// 회원정보수정
	boolean modifyUser(User user);
	
	// 유저 권한 코드 조회 (1:ADMIN, 2:USER, 3:SELLER)
	int readUserAuthority(int user);
}
