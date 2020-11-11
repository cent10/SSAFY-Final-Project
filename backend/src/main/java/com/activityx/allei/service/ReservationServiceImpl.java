package com.activityx.allei.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activityx.allei.dao.ProductDao;
import com.activityx.allei.dao.ReservationDao;
import com.activityx.allei.dao.ReviewDao;
import com.activityx.allei.dao.ShopDao;
import com.activityx.allei.dto.DetailReservationDto;
import com.activityx.allei.dto.ProductDto;
import com.activityx.allei.dto.ReservationBean;
import com.activityx.allei.dto.ReservationDto;
import com.activityx.allei.dto.ReservationsByUserBean;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	ReservationDao reservationDao;

	@Autowired
	ProductDao productDao;
	
	@Autowired
	ShopDao shopDao;
	
	@Autowired
	ReviewDao reviewDao;
	
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
	public ArrayList<ReservationsByUserBean> readAllReservation(int id) {
		List<ReservationDto> reservations = reservationDao.readAllReservation(id);
		System.out.println("예약목록 개수"+reservations.size());
		ArrayList<ReservationsByUserBean> beans = new ArrayList<ReservationsByUserBean>();
		for(ReservationDto res : reservations) {
			ReservationsByUserBean bean = new ReservationsByUserBean();
			bean.setId(res.getId());		// 예약번호
			bean.setUser(res.getUser());	// 예약자번호 (user.id)
			bean.setShop(res.getShop());	// 업체 번호 (shop.id)
			bean.setShopName(shopDao.getNamebyId(res.getShop()));	// 업체 이름
			bean.setDate(res.getDate());	// 에약 일시
			bean.setProducts(new ArrayList<>());	// 예약 상품 내역
			bean.setNums(new ArrayList<>());		// 예약 상품 개수
			ArrayList<DetailReservationDto> details = reservationDao.readDetailReservation(res.getId());
			for(DetailReservationDto detail : details) {
				bean.setStart(detail.getStart());	// 예약 시작일
				bean.setEnd(detail.getEnd());		// 예약 종료일
				bean.getNums().add(detail.getNum());
				bean.getProducts().add(productDao.readProduct(detail.getProduct()));
			}
			bean.setReviewed(reviewDao.reviewWrited(res.getId()));
			
			beans.add(bean);
		}
		
		return beans;
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
