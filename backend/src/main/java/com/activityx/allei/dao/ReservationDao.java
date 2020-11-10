package com.activityx.allei.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.activityx.allei.dto.DetailReservationDto;
import com.activityx.allei.dto.ReservationDto;

@Mapper
public interface ReservationDao {
	
	// 예약정보 등록
	public int createReservation(ReservationDto reservationDto);
	
	// 상세예약정보 등록
	public int createDetailReservation(int reservation, int product, int num, Date start, Date end);
	
	// 예약정보 조회
	public ReservationDto readReservation(int id);
	
	// 상세예약정보 조회
	public ArrayList<DetailReservationDto> readDetailReservation(int reservation);
	
	// 사용자의 예약정보 리스트 조회
	public List<ReservationDto> readAllReservation(int id);
	
	// 예약정보 등록
	public int deleteReservation(int id);
	
	// 최근 예약번호
	public Integer getLastReservationId();
	
	// 결제 시작시 tid 추가
	public int addTid(String tid, int id);
	
	// tid로 예약정보 검색
	public ReservationDto getReservationByTid(String tid);
	
	// 결제완료시 state변경
	public int changeState(String tid);
}
