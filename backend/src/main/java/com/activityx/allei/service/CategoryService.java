package com.activityx.allei.service;

import java.util.ArrayList;

import com.activityx.allei.dto.CategoryDto;

public interface CategoryService {
	//카테고리 목록 가져오기
	ArrayList<CategoryDto> getAllCategories();
}
