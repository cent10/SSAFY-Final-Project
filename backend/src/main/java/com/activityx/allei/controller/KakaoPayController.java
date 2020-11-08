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
import com.activityx.allei.dto.KakaoPayApprovalDto;
import com.activityx.allei.dto.KakaoPayBean;
import com.activityx.allei.dto.KakaoPayReadyDto;
import com.activityx.allei.dto.SampleDto;
import com.activityx.allei.service.KakaoPayServiceImpl;
import com.activityx.allei.service.SampleService;

import io.swagger.annotations.ApiOperation;

//http://localhost:8080/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/pay")
public class KakaoPayController {
	
	private static final Logger logger = LoggerFactory.getLogger(KakaoPayController.class);
	
	@Autowired
	KakaoPayServiceImpl service;
	
	@ApiOperation(value = "카카오 페이로 결제", response = BasicResponse.class)
	@GetMapping("kakao")
	public ResponseEntity<BasicResponse> kakaoPay(@RequestParam KakaoPayBean bean) {
		logger.debug("KakaoPay test : kakaoPayReady - 호츌");
		final BasicResponse result = new BasicResponse();
		KakaoPayReadyDto data = service.kakaoPayReady(bean);
		if(data != null) {
			result.status = true;
			result.data = data;
		}
		else {
			result.status = false;
			result.msg = "결제 요청 실패";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "카카오 페이 결제 성공", response = BasicResponse.class)
	@GetMapping("kakao/success")
	public ResponseEntity<BasicResponse> kakaoPaySuccess(@RequestParam String pg_token, @RequestBody KakaoPayBean bean) {
		logger.debug("KakaoPay test : kakaoPayApproval - 호츌");
		final BasicResponse result = new BasicResponse();
		KakaoPayApprovalDto data = service.kakaoPayApproval(pg_token, bean);
		if(data != null) {
			result.status = true;
			result.data = data;
		}
		else {
			result.status = false;
			result.msg = "결제 정보 로딩 실패";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
}
