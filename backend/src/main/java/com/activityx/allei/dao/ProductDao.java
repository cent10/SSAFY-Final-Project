package com.activityx.allei.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.activityx.allei.dto.ProductDto;

@Mapper
public interface ProductDao {

	// 상품 등록
	public int create(ProductDto productDto);
}
