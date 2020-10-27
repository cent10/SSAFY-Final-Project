package com.activityx.allei.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	private ResponseEntity<BasicResponse> readAllReservations(@RequestBody int id) {
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
}
