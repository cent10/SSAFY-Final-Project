package com.activityx.allei.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		ShopCategoryDto shopCategoryDto = new ShopCategoryDto(0, shopDto.getId(), categoryDao.readCode(categoryName));
		int check2 = shopCategoryDao.createShopCategory(shopCategoryDto);
		
		return check1 + check2 > 1;
	}

	@Override
	public Map<String, Object> read(int id) {
		Map<String, Object> map = new HashMap<>();
		ShopDto shopDto = shopDao.read(id);
		String category = categoryDao.readName(id);
		map.put("shop", shopDto);
		map.put("category", category);
		return map;
	}

	@Override
	public List<ShopDto> readAll() {
		return shopDao.readAll();
	}

	@Override
	public Map<String, Object> search(String searchword) {
		Map<String, Object> map = new HashMap<>();
		List<ShopDto> leisureShops = shopDao.searchLeisureShops(searchword);
		List<ShopDto> rentalShops = shopDao.searchRentalShops(searchword);
		map.put("leisureShops", leisureShops);
		map.put("rentalShops", rentalShops);
		return map;
	}
	
	@Override
	public Map<String, Object> searchLimit(String searchword) {
		Map<String, Object> map = new HashMap<>();
		List<ShopDto> leisureShops = shopDao.searchLeisureShopsLimit(searchword);
		List<ShopDto> rentalShops = shopDao.searchRentalShopsLimit(searchword);
		map.put("leisureShops", leisureShops);
		map.put("rentalShops", rentalShops);
		return map;
	}
	
	@Override
	public List<ShopDto> detailSearchLeisureShop(int num, int minPrice, int maxPrice, String region, String category) {
		List<ShopDto> shopList = shopDao.detailSearchLeisureShop(minPrice, maxPrice, region, category);
		List<ShopDto> data = new ArrayList<>();
		for (int i = num * 12; i < num * 12 + 12; i++) {
			if (i >= shopList.size()) {
				break;
			}
			data.add(shopList.get(i));
		}
		
		return data;
	}
	
	@Override
	public List<ShopDto> detailSearchRentalShop(int num, int minPrice, int maxPrice, String region, String category) {
		List<ShopDto> shopList = shopDao.detailSearchRentalShop(minPrice, maxPrice, region, category);
		List<ShopDto> data = new ArrayList<>();
		for (int i = num * 12; i < num * 12 + 12; i++) {
			if (i >= shopList.size()) {
				break;
			}
			data.add(shopList.get(i));
		}
		
		return data;
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
