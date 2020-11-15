package com.activityx.allei.service;

import java.util.List;
import java.util.Map;

import com.activityx.allei.dto.ShopBean;
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
	
	// 업체 검색 (레저 서비스 업체 10개, 장비 대여 업체 10개)
	Map<String, Object> searchLimit(String searchword);
	
	// 레저 서비스 업체 상세 검색 (기본: id순)
	List<ShopBean> detailSearchLeisureShop(int num, int minPrice, int maxPrice, String region, String category);
	
	// 장비 대여 업체 상세 검색 (기본: id순)
	List<ShopBean> detailSearchRentalShop(int num, int minPrice, int maxPrice, String region, String category);
	
	// 레저 서비스 업체 상세 검색 (가격 낮은 순)
	List<ShopBean> detailSearchLeisureShopOrderByPrice(int num, int minPrice, int maxPrice, String region, String category);
	
	// 장비 대여 업체 상세 검색 (가격 낮은 순)
	List<ShopBean> detailSearchRentalShopOrderByPrice(int num, int minPrice, int maxPrice, String region, String category);
	
	// 레저 서비스 업체 상세 검색 (평점 높은 순)
	List<ShopBean> detailSearchLeisureShopOrderByRate(int num, int minPrice, int maxPrice, String region, String category);
	
	// 장비 대여 업체 상세 검색 (평점 높은 순)
	List<ShopBean> detailSearchRentalShopOrderByRate(int num, int minPrice, int maxPrice, String region, String category);
	
	// 업체 수정
	boolean update(ShopDto shopDto);
	
	// 업체 삭제
	boolean delete(int id);
	
	// 업체 이미지 저장
	boolean updateImg(ShopDto shopDto);
	
	// 업체 설명 이미지 저장
	boolean updateImgDesc(ShopDto shopDto);
	
	// 사용자 id로 업체 id 찾기
	public Integer getShopIdByUser(int user); 
}
