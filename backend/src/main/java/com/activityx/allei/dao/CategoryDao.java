package com.activityx.allei.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryDao {

	// 카테고리 이름으로 카테고리 코드 조회
	public int readCode(String name);
}
