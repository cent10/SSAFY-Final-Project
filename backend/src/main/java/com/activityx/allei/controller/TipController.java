package com.activityx.allei.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activityx.allei.dto.BasicResponse;
import com.activityx.allei.dto.TipDto;
import com.activityx.allei.service.TipService;

import io.swagger.annotations.ApiOperation;

//http://localhost:8080/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/tip")
public class TipController {
	
	private static final Logger logger = LoggerFactory.getLogger(TipController.class);
	
	@Autowired
	TipService service;
	
	@ApiOperation(value = "팁 게시글 작성", response = BasicResponse.class)
	@PostMapping("create")
	public ResponseEntity<BasicResponse> createTip(@RequestBody TipDto tip) {
		logger.debug("Tip Board test : createTip - 호츌");
		final BasicResponse result = new BasicResponse();
		if(service.createTip(tip)){
			result.status = true;
		}else{
			result.status = false;
			result.msg = "팁 게시글 작성에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}

}
