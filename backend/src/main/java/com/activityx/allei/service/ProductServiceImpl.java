package com.activityx.allei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activityx.allei.dao.ProductDao;
import com.activityx.allei.dto.ProductDto;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;

	@Override
	public boolean create(ProductDto productDto) {
		return productDao.create(productDto) == 1;
	}

	@Override
	public List<ProductDto> readAll(int shop) {
		return productDao.readAll(shop);
	}

	@Override
	public boolean update(ProductDto productDto) {
		return productDao.update(productDto) == 1;
	}

	@Override
	public boolean delete(int id) {
		return productDao.delete(id) == 1;
	}
}
