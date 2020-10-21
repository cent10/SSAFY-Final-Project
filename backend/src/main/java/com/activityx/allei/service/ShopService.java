package com.activityx.allei.service;

import java.util.List;

import com.activityx.allei.dto.ShopDto;

public interface ShopService {
	
	// 업체 등록
	void create(ShopDto shopDto);
	
	// 업체 상세 조회
	ShopDto read(int id);
	
	// 업체 검색
	List<ShopDto> search(String searchword);
	
}
