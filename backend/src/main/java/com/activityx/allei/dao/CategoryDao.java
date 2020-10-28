package com.activityx.allei.dao;

import java.util.ArrayList;

import com.activityx.allei.dto.CategoryDto;

public interface CategoryDao {
	//카테고리 목록 가져오기
	ArrayList<CategoryDto> getAllCategories();
}
