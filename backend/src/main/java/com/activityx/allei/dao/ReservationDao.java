package com.activityx.allei.dao;

import org.apache.ibatis.annotations.Mapper;

import com.activityx.allei.dto.DetailReservationDto;
import com.activityx.allei.dto.ReservationDto;

@Mapper
public interface ReservationDao {
	
	// 예약정보 조회
	public ReservationDto readReservation(int id);
	
	// 상세예약정보 조회
	public DetailReservationDto readDetailReservation(int reservation);
}
