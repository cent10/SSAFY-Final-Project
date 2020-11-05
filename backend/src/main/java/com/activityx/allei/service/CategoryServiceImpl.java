package com.activityx.allei.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activityx.allei.dao.CategoryDao;
import com.activityx.allei.dto.CategoryDto;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao dao;
	
	@Override
	public ArrayList<CategoryDto> getAllCategories() {
		return dao.getAllCategories();
	}
}
