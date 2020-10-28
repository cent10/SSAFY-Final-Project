package com.activityx.allei.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activityx.allei.dao.ProductDao;
import com.activityx.allei.dao.ReservationDao;
import com.activityx.allei.dto.DetailReservationDto;
import com.activityx.allei.dto.ProductDto;
import com.activityx.allei.dto.ReservationDto;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	ReservationDao reservationDao;

	@Autowired
	ProductDao productDao;
	
	@Override
	public boolean create(ReservationDto reservationDto, int product, int num) {
		int check1 = reservationDao.createReservation(reservationDto);
		int check2 = reservationDao.createDetailReservation(reservationDto.getId(), product, num);
		return (check1 + check2) > 1;
	}
	
	@Override
	public Map<String, Object> readReservation(int id) {
		Map<String, Object> map = new HashMap<>();
		ReservationDto reservationDto = reservationDao.readReservation(id);
		DetailReservationDto detailReservationDto = reservationDao.readDetailReservation(id);
		ProductDto productDto = productDao.readProduct(detailReservationDto.getProduct());
		map.put("reservation", reservationDto);
		map.put("detailReservation", detailReservationDto);
		map.put("product", productDto);
		return map;
	}

	@Override
	public List<ReservationDto> readAllReservation(int id) {
		return reservationDao.readAllReservation(id);
	}

	@Override
	public boolean deleteReservation(int id) {
		return reservationDao.deleteReservation(id) == 1;
	}
	
}
