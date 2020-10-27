package com.activityx.allei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activityx.allei.dao.ReservationDao;
import com.activityx.allei.dto.DetailReservationDto;
import com.activityx.allei.dto.ReservationDto;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	ReservationDao reservationDao;

	@Override
	public ReservationDto readReservation(int id) {
		return reservationDao.readReservation(id);
	}

	@Override
	public DetailReservationDto readDetailReservation(int reservation) {
		return reservationDao.readDetailReservation(reservation);
	}
	
}
