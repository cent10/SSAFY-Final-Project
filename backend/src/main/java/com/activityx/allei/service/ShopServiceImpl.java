package com.activityx.allei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activityx.allei.dao.CategoryDao;
import com.activityx.allei.dao.ShopCategoryDao;
import com.activityx.allei.dao.ShopDao;
import com.activityx.allei.dto.ShopCategoryDto;
import com.activityx.allei.dto.ShopDto;

@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	ShopDao shopDao;
	
	@Autowired
	ShopCategoryDao shopCategoryDao;
	
	@Autowired
	CategoryDao categoryDao;

	@Override
	public boolean create(ShopDto shopDto, String categoryName) {
		int check1 = shopDao.createShop(shopDto);
		System.out.println("shopDto: " + shopDto);
		ShopCategoryDto shopCategoryDto = new ShopCategoryDto(0, shopDto.getId(), categoryDao.readCode(categoryName));
		System.out.println("shopCategoryDto: " + shopCategoryDto);
		int check2 = shopCategoryDao.createShopCategory(shopCategoryDto);
		
		return check1 + check2 > 1;
	}

	@Override
	public ShopDto read(int id) {
		return shopDao.read(id);
	}

	@Override
	public List<ShopDto> readAll() {
		return shopDao.readAll();
	}

	@Override
	public List<ShopDto> search(String searchword) {
		return shopDao.search(searchword);
	}
	
	@Override
	public List<ShopDto> detailedSearch(int minPrice, int maxPrice, String region, String category) {
		return shopDao.detailedSearch(minPrice, maxPrice, region, category);
	}
	
	@Override
	public boolean update(ShopDto shopDto) {
		return shopDao.update(shopDto) == 1;
	}

	@Override
	public boolean delete(int id) {
		return shopDao.delete(id) == 1;
	}

}
