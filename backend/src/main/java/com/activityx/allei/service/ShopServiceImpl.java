package com.activityx.allei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activityx.allei.dao.ShopDao;
import com.activityx.allei.dto.ShopDto;
import com.activityx.allei.error.exception.EntityNotFoundException;
import com.activityx.allei.error.exception.IncorrectFormatException;

@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	ShopDao shopDao;

	@Override
	public void create(ShopDto shopDto) {
		if (shopDao.create(shopDto) < 1) {
			throw new IncorrectFormatException(String.valueOf(shopDto.getId()));
		}
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
	public void update(ShopDto shopDto) {
		try {
			shopDao.update(shopDto);
		} catch (Exception e) {
			if(e.getMessage().contains("For")) {
				throw new EntityNotFoundException(String.valueOf(shopDto.getId()));
			}
		}
	}
}
