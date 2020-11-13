package com.activityx.allei.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAuthorityDao {
	
	// 계정 권한 부여
	public int create(int user, int code);
	
	// 계정 권한 조회
	public int check(int user);
	
	// 계정 권한 변경
	public int update(int user, int code);
}
