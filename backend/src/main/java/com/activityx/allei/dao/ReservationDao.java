package com.activityx.allei.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.activityx.allei.dto.DetailReservationDto;
import com.activityx.allei.dto.ReservationDto;

@Mapper
public interface ReservationDao {
	
	// 예약정보 등록
	public int createReservation(ReservationDto reservationDto);
	
	// 상세예약정보 등록
	public int createDetailReservation(int reservation, int product, int num, String start, String end);
	
	// 예약정보 조회
	public ReservationDto readReservation(int id);
	
	// 상세예약정보 조회
	public DetailReservationDto readDetailReservation(int reservation);
	
	// 사용자의 예약정보 리스트 조회
	public List<ReservationDto> readAllReservation(int id);
	
	// 예약정보 등록
	public int deleteReservation(int id);
}
