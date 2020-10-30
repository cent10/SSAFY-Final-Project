package com.activityx.allei.service;

import java.util.List;
import java.util.Map;

import com.activityx.allei.dto.ShopDto;

public interface ShopService {
	
	// 업체 등록
	boolean create(ShopDto shopDto, String categoryName);
	
	// 업체 상세 조회
	Map<String, Object> read(int id);
	
	// 업체 리스트 조회
	List<ShopDto> readAll();
	
	// 업체 검색
	Map<String, Object> search(String searchword);
	
	// 업체 상세 검색
	List<ShopDto> detailedSearch(int minPrice, int maxPrice, String region, String category);
	
	// 업체 수정
	boolean update(ShopDto shopDto);
	
	// 업체 삭제
	boolean delete(int id);
}
