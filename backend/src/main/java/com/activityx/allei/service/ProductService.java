package com.activityx.allei.service;

import java.util.List;

import com.activityx.allei.dto.ProductDto;

public interface ProductService {
	
	// 상품 등록
	boolean create(ProductDto productDto);
	
	// 해당 업체의 상품 리스트 조회
	List<ProductDto> readAll(int shop);
	
	// 상품 수정
	boolean update(ProductDto productDto);
	
	// 상품 삭제
	boolean delete(int id);
}
