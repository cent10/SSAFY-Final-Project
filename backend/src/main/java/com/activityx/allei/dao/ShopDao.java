package com.activityx.allei.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.activityx.allei.dto.ShopDto;

@Mapper
public interface ShopDao {
	
	// 업체 등록
	public int create(ShopDto shopDto);
	
	// 업체 상세 조회
	public ShopDto read(int id);
	
	// 업체 리스트 조회
	public List<ShopDto> readAll();
	
	// 업체 검색
	public List<ShopDto> search(String searchword);
	
	// 업체 상세 검색
	public List<ShopDto> detailedSearch(int minPrice, int maxPrice, String region, String category);
	
	// 업체 수정
	public int update(ShopDto shopDto);
	
	// 업체 삭제
	public int delete(int id);
}
