package com.activityx.allei.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activityx.allei.dao.CategoryDao;
import com.activityx.allei.dao.ProductDao;
import com.activityx.allei.dao.ShopCategoryDao;
import com.activityx.allei.dao.ShopDao;
import com.activityx.allei.dto.ShopBean;
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
	
	@Autowired
	ProductDao productDao;

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
		Float rate = shopDao.readRate(id);
		map.put("shop", shopDto);
		map.put("category", category);
		map.put("rate", rate);
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
	public List<ShopBean> detailSearchLeisureShop(int num, int minPrice, int maxPrice, String region, String category) {
		List<ShopDto> shopDtoList = shopDao.detailSearchLeisureShop(minPrice, maxPrice, region, category);
		List<ShopBean> shopBeanList = new ArrayList<>();
		for(ShopDto s : shopDtoList) {
			ShopBean shopBean = new ShopBean(s.getId(), s.getAdmin(), s.getName(), s.getAddress(),
					s.getPhone(), s.isClassification(), s.getNumber(), s.getDescription(), s.getImgDesc(), s.getImg(),
					s.getRegion(), shopDao.readRate(s.getId()), productDao.readProductMinPrice(s.getId()));
			shopBeanList.add(shopBean);
		}
		
		List<ShopBean> data = new ArrayList<>();
		for (int i = num * 12; i < num * 12 + 12; i++) {
			if (i >= shopBeanList.size()) {
				break;
			}
			data.add(shopBeanList.get(i));
		}
		
		return data;
	}
	
	@Override
	public List<ShopBean> detailSearchRentalShop(int num, int minPrice, int maxPrice, String region, String category) {
		List<ShopDto> shopDtoList = shopDao.detailSearchRentalShop(minPrice, maxPrice, region, category);
		List<ShopBean> shopBeanList = new ArrayList<>();
		for(ShopDto s : shopDtoList) {
			ShopBean shopBean = new ShopBean(s.getId(), s.getAdmin(), s.getName(), s.getAddress(),
					s.getPhone(), s.isClassification(), s.getNumber(), s.getDescription(), s.getImgDesc(), s.getImg(),
					s.getRegion(), shopDao.readRate(s.getId()), productDao.readProductMinPrice(s.getId()));
			shopBeanList.add(shopBean);
		}
		
		List<ShopBean> data = new ArrayList<>();
		for (int i = num * 12; i < num * 12 + 12; i++) {
			if (i >= shopBeanList.size()) {
				break;
			}
			data.add(shopBeanList.get(i));
		}
		
		return data;
	}
	
	@Override
	public List<ShopBean> detailSearchLeisureShopOrderByPrice(int num, int minPrice, int maxPrice, String region, String category) {
		List<ShopDto> shopDtoList = shopDao.detailSearchLeisureShop(minPrice, maxPrice, region, category);
		List<ShopBean> shopBeanList = new ArrayList<>();
		for(ShopDto s : shopDtoList) {
			ShopBean shopBean = new ShopBean(s.getId(), s.getAdmin(), s.getName(), s.getAddress(),
					s.getPhone(), s.isClassification(), s.getNumber(), s.getDescription(), s.getImgDesc(), s.getImg(),
					s.getRegion(), shopDao.readRate(s.getId()), productDao.readProductMinPrice(s.getId()));
			shopBeanList.add(shopBean);
		}
		
		Collections.sort(shopBeanList, new Comparator<ShopBean>() {
			@Override
			public int compare(ShopBean o1, ShopBean o2) {
				return o1.getMinPrice() - o2.getMinPrice();
			}
		});
		
		List<ShopBean> data = new ArrayList<>();
		for (int i = num * 12; i < num * 12 + 12; i++) {
			if (i >= shopBeanList.size()) {
				break;
			}
			data.add(shopBeanList.get(i));
		}
		
		return data;
	}

	@Override
	public List<ShopBean> detailSearchRentalShopOrderByPrice(int num, int minPrice, int maxPrice, String region, String category) {
		List<ShopDto> shopDtoList = shopDao.detailSearchRentalShop(minPrice, maxPrice, region, category);
		List<ShopBean> shopBeanList = new ArrayList<>();
		for(ShopDto s : shopDtoList) {
			ShopBean shopBean = new ShopBean(s.getId(), s.getAdmin(), s.getName(), s.getAddress(),
					s.getPhone(), s.isClassification(), s.getNumber(), s.getDescription(), s.getImgDesc(), s.getImg(),
					s.getRegion(), shopDao.readRate(s.getId()), productDao.readProductMinPrice(s.getId()));
			shopBeanList.add(shopBean);
		}
		
		Collections.sort(shopBeanList, new Comparator<ShopBean>() {
			@Override
			public int compare(ShopBean o1, ShopBean o2) {
				return o1.getMinPrice() - o2.getMinPrice();
			}
		});
		
		List<ShopBean> data = new ArrayList<>();
		for (int i = num * 12; i < num * 12 + 12; i++) {
			if (i >= shopBeanList.size()) {
				break;
			}
			data.add(shopBeanList.get(i));
		}
		
		return data;
	}

	@Override
	public List<ShopBean> detailSearchLeisureShopOrderByRate(int num, int minPrice, int maxPrice, String region, String category) {
		List<ShopDto> shopDtoList = shopDao.detailSearchLeisureShop(minPrice, maxPrice, region, category);
		List<ShopBean> shopBeanList = new ArrayList<>();
		for(ShopDto s : shopDtoList) {
			ShopBean shopBean = new ShopBean(s.getId(), s.getAdmin(), s.getName(), s.getAddress(),
					s.getPhone(), s.isClassification(), s.getNumber(), s.getDescription(), s.getImgDesc(), s.getImg(),
					s.getRegion(), shopDao.readRate(s.getId()), productDao.readProductMinPrice(s.getId()));
			shopBeanList.add(shopBean);
		}
		
		Collections.sort(shopBeanList, new Comparator<ShopBean>() {
			@Override
			public int compare(ShopBean o1, ShopBean o2) {
				return (int) (o2.getAvgRate() - o1.getAvgRate());
			}
		});
		
		List<ShopBean> data = new ArrayList<>();
		for (int i = num * 12; i < num * 12 + 12; i++) {
			if (i >= shopBeanList.size()) {
				break;
			}
			data.add(shopBeanList.get(i));
		}
		
		return data;
	}

	@Override
	public List<ShopBean> detailSearchRentalShopOrderByRate(int num, int minPrice, int maxPrice, String region, String category) {
		List<ShopDto> shopDtoList = shopDao.detailSearchRentalShop(minPrice, maxPrice, region, category);
		List<ShopBean> shopBeanList = new ArrayList<>();
		for(ShopDto s : shopDtoList) {
			ShopBean shopBean = new ShopBean(s.getId(), s.getAdmin(), s.getName(), s.getAddress(),
					s.getPhone(), s.isClassification(), s.getNumber(), s.getDescription(), s.getImgDesc(), s.getImg(),
					s.getRegion(), shopDao.readRate(s.getId()), productDao.readProductMinPrice(s.getId()));
			shopBeanList.add(shopBean);
		}
		
		Collections.sort(shopBeanList, new Comparator<ShopBean>() {
			@Override
			public int compare(ShopBean o1, ShopBean o2) {
				return (int) (o2.getAvgRate() - o1.getAvgRate());
			}
		});
		
		List<ShopBean> data = new ArrayList<>();
		for (int i = num * 12; i < num * 12 + 12; i++) {
			if (i >= shopBeanList.size()) {
				break;
			}
			data.add(shopBeanList.get(i));
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

	@Override
	public boolean updateImgDesc(ShopDto shopDto) {
		return shopDao.updateImgDesc(shopDto) == 1;
	}

}
