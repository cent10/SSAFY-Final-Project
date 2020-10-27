package com.activityx.allei.service;

import com.activityx.allei.dto.DetailReservationDto;
import com.activityx.allei.dto.ReservationDto;

public interface ReservationService {
	
	// 예약정보 조회
	public ReservationDto readReservation(int id);
	
	// 상세예약정보 조회
	public DetailReservationDto readDetailReservation(int reservation);
}
