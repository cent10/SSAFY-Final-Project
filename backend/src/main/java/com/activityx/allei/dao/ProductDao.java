package com.activityx.allei.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.activityx.allei.dto.ProductDto;

@Mapper
public interface ProductDao {

	// 상품 등록
	public int create(ProductDto productDto);
	
	// 해당 업체의 상품 리스트 조회
	public List<ProductDto> readAll(int shop);
	
	// 상품 수정
	public int update(ProductDto productDto);
	
	// 상품 삭제
	public int delete(int id);
	
	// 예약정보에 해당하는 상품 조회
	public ProductDto readProduct(int product);
}
