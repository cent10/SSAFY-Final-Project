package com.activityx.allei.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.activityx.allei.dto.BasicResponse;
import com.activityx.allei.dto.ReservationBean;
import com.activityx.allei.dto.ReservationDto;
import com.activityx.allei.service.ReservationService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/reservations")
public class ReservationController {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@Autowired
	ReservationService reservationService;
	
	@ApiOperation(value = "예약하기", response = BasicResponse.class)
	@PostMapping("")
	private ResponseEntity<BasicResponse> createReservation(@RequestBody ReservationBean bean) {
		logger.debug("예약하기");
		final BasicResponse result = new BasicResponse();
		if (reservationService.create(bean)) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "예약하기가 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "예약 번호로 예약정보 조회", response = BasicResponse.class)
	@GetMapping("/{id}")
	private ResponseEntity<BasicResponse> readReservation(@PathVariable("id") int id) {
		logger.debug("예약 번호로 예약정보 조회");
		final BasicResponse result = new BasicResponse();
		Map<String, Object> map = reservationService.readReservation(id);
		if (map != null) {
			result.status = true;
			result.data = map;
		} else {
			result.status = false;
			result.msg = "해당 예약정보가 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "특정 사용자의 모든 예약정보 리스트 조회", response = BasicResponse.class)
	@GetMapping("")
	private ResponseEntity<BasicResponse> readAllReservations(@RequestParam(value = "id") int id) {
		logger.debug("특정 사용자의 모든 예약정보 리스트 조회");
		final BasicResponse result = new BasicResponse();
		List<ReservationDto> reservationList = reservationService.readAllReservation(id);
		if (reservationList != null) {
			result.status = true;
			result.data = reservationList;
		} else {
			result.status = false;
			result.msg = "해당 사용자의 예약정보 리스트가 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "예약취소", response = BasicResponse.class)
	@DeleteMapping("/{id}")
	private ResponseEntity<BasicResponse> deleteReservation(@PathVariable("id") int id) {
		logger.debug("예약취소");
		final BasicResponse result = new BasicResponse();
		if (reservationService.deleteReservation(id)) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "예약취소가 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "최근 예약번호 조회", response = BasicResponse.class)
	@GetMapping("lastid")
	private ResponseEntity<BasicResponse> getLastReservationId() {
		logger.debug("최근 예약번호 조회");
		final BasicResponse result = new BasicResponse();
		Integer data = reservationService.getLastReservationId();
		if (data != null) {
			result.status = true;
			result.data = data;
		} else {
			result.status = false;
			result.msg = "조회 실패 했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
}
