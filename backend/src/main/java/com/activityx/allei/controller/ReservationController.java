package com.activityx.allei.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activityx.allei.dto.BasicResponse;
import com.activityx.allei.dto.DetailReservationDto;
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
	private ResponseEntity<BasicResponse> readShop(@PathVariable("id") int id) {
		logger.debug("예약정보 조회");
		final BasicResponse result = new BasicResponse();
		Map<String, Object> map = new HashMap<>();
		ReservationDto reservationDto = reservationService.readReservation(id);
		DetailReservationDto detailReservationDto = reservationService.readDetailReservation(id);
		map.put("reservation", reservationDto);
		map.put("detailReservation", detailReservationDto);
		if (reservationDto != null && detailReservationDto != null) {
			result.status = true;
			result.data = map;
		} else {
			result.status = false;
			result.msg = "해당 예약정보가 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
}
