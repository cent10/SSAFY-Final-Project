package com.activityx.allei.dao;

import org.apache.ibatis.annotations.Mapper;

import com.activityx.allei.dto.ShopCategoryDto;

@Mapper
public interface ShopCategoryDao {

	// 업체-카테고리 등록
	public int createShopCategory(ShopCategoryDto shopCategoryDto);
}
