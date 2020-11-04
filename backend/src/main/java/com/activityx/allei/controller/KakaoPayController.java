package com.activityx.allei.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.activityx.allei.dto.BasicResponse;
import com.activityx.allei.dto.SampleDto;
import com.activityx.allei.service.SampleService;

import io.swagger.annotations.ApiOperation;

//http://localhost:8080/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/pay")
public class KakaoPayController {
	
	private static final Logger logger = LoggerFactory.getLogger(KakaoPayController.class);
	
	@ApiOperation(value = "샘플", response = BasicResponse.class)
	@GetMapping("kakao")
	public ResponseEntity<BasicResponse> kakaoPay() {
		logger.debug("Rest Server test : login - 호츌");
		final BasicResponse result = new BasicResponse();
		boolean sample = true;
		if(sample){
			result.status = true;
		}else{
			result.status = false;
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
}
