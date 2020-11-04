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
	private ResponseEntity<BasicResponse> createReservation(@RequestParam(value = "product (상품 아이디)") int product,
															@RequestParam(value = "num (예약 수량)") int num,
															@RequestParam(value = "start (시작날짜) (yyyy-MM-dd)") String start,
															@RequestParam(value = "end (끝날짜) (yyyy-MM-dd)") String end,
															@RequestBody ReservationDto reservationDto) {
		logger.debug("예약하기");
		final BasicResponse result = new BasicResponse();
		if (reservationService.create(reservationDto, product, num, start, end)) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "예약하기가 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "예약정보 조회", response = BasicResponse.class)
	@GetMapping("/{id}")
	private ResponseEntity<BasicResponse> readReservation(@PathVariable("id") int id) {
		logger.debug("예약정보 조회");
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
	
	@ApiOperation(value = "예약정보 리스트 조회", response = BasicResponse.class)
	@GetMapping("")
	private ResponseEntity<BasicResponse> readAllReservations(@RequestParam(value = "id") int id) {
		logger.debug("예약정보 리스트 조회");
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
}
