package com.activityx.allei.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activityx.allei.dto.BasicResponse;
import com.activityx.allei.dto.CategoryDto;
import com.activityx.allei.service.CategoryService;

import io.swagger.annotations.ApiOperation;

//http://localhost:8080/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/category")
public class CategoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	CategoryService service;
	
	@ApiOperation(value = "카테고리 목록을 반환한다.", response = BasicResponse.class)
	@GetMapping("all")
	public ResponseEntity<BasicResponse> getAllCategories() {
		logger.debug("Rest Server test : login - 호츌");
		final BasicResponse result = new BasicResponse();
		ArrayList<CategoryDto> data = service.getAllCategories();
		if(data != null){
			result.status = true;
			result.data = data;
		}else{
			result.status = false;
			result.msg = "카테고리가 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
}
