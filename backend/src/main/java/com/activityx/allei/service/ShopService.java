package com.activityx.allei.service;

import java.util.List;

import com.activityx.allei.dto.ShopDto;

public interface ShopService {
	
	// 업체 등록
	boolean create(ShopDto shopDto);
	
	// 업체 상세 조회
	ShopDto read(int id);
	
	// 업체 리스트 조회
	List<ShopDto> readAll();
	
	// 업체 검색
	List<ShopDto> search(String searchword);
	
	// 업체 수정
	boolean update(ShopDto shopDto);
	
	// 업체 삭제
	boolean delete(int id);
}
