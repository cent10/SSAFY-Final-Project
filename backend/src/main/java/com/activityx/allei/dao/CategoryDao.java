package com.activityx.allei.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.activityx.allei.dto.CategoryDto;

@Mapper
public interface CategoryDao {

	// 카테고리 이름으로 카테고리 코드 조회
	public int readCode(String name);
	
	// 업체 아이디로 카테고리 이름 조회
	public String readName(int shop);
	
	//카테고리 목록 가져오기
	ArrayList<CategoryDto> getAllCategories();
}
