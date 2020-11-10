package com.activityx.allei.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activityx.allei.dao.ProductDao;
import com.activityx.allei.dao.ReservationDao;
import com.activityx.allei.dto.DetailReservationDto;
import com.activityx.allei.dto.ProductDto;
import com.activityx.allei.dto.ReservationBean;
import com.activityx.allei.dto.ReservationDto;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	ReservationDao reservationDao;

	@Autowired
	ProductDao productDao;
	
	@Override
	public int create(ReservationBean bean) {
		ReservationDto reservationDto = new ReservationDto();
		reservationDto.setShop(bean.getShop());
		reservationDto.setUser(bean.getUser());
		
		System.out.println(reservationDto.getId());
		int check1 = reservationDao.createReservation(reservationDto);
		System.out.println(reservationDto.getId());
		int i=0, check2=0;
		for(i=0; i<bean.getProducts().size(); i++) {
			check2 += reservationDao.createDetailReservation(reservationDto.getId(), bean.getProducts().get(i), bean.getNums().get(i), bean.getStart(), bean.getEnd());			
		}
		
		if((check1==1) && (check2==i))
			return reservationDto.getId();
		
		return -1;
	}
	
	@Override
	public Map<String, Object> readReservation(int id) {
		Map<String, Object> map = new HashMap<>();
		ReservationDto reservationDto = reservationDao.readReservation(id);
		ArrayList<DetailReservationDto> detailReservations = reservationDao.readDetailReservation(id);
		ArrayList<ProductDto> products = new ArrayList<ProductDto>();
		for(DetailReservationDto dr : detailReservations) {
			ProductDto productDto = productDao.readProduct(dr.getProduct());
			products.add(productDto);
		}
		map.put("reservation", reservationDto);
		map.put("detailReservation", detailReservations);
		map.put("product", products);
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
	
	@Override
	public Integer getLastReservationId() {
		return reservationDao.getLastReservationId();
	}

}
