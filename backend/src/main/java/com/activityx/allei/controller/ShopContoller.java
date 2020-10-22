package com.activityx.allei.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activityx.allei.dto.BasicResponse;
import com.activityx.allei.dto.ProductDto;
import com.activityx.allei.dto.ShopDto;
import com.activityx.allei.service.ProductService;
import com.activityx.allei.service.ShopService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/shops")
public class ShopContoller {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@Autowired
	ShopService shopService;
	
	@Autowired
	ProductService productService;
	
	@ApiOperation(value = "업체 등록")
	@PostMapping("")
	private ResponseEntity<BasicResponse> create(@RequestBody ShopDto shopDto) {
		logger.debug("업체 등록");
		final BasicResponse result = new BasicResponse();
		if (shopService.create(shopDto)) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "업체 등록에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "업체 상세 조회", response = BasicResponse.class)
	@GetMapping("/{id}")
	private ResponseEntity<BasicResponse> read(@PathVariable("id") int id) {
		logger.debug("업체 상세 조회");
		final BasicResponse result = new BasicResponse();
		ShopDto shopDto = shopService.read(id);
		if (shopDto != null) {
			result.status = true;
			result.data = shopDto;
		} else {
			result.status = false;
			result.msg = "해당 업체가 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "업체 리스트 조회", response = BasicResponse.class)
	@GetMapping("")
	private ResponseEntity<BasicResponse> readAll() {
		logger.debug("업체 리스트 조회");
		final BasicResponse result = new BasicResponse();
		List<ShopDto> shopList = shopService.readAll();
		if (shopList != null) {
			result.status = true;
			result.data = shopList;
		} else {
			result.status = false;
			result.msg = "업체가 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "업체 검색", response = BasicResponse.class)
	@GetMapping("/search/{searchword}")
	private ResponseEntity<BasicResponse> search(@PathVariable("searchword") String searchword) {
		logger.debug("업체 검색");
		final BasicResponse result = new BasicResponse();
		List<ShopDto> shopList = shopService.search(searchword);
		if (shopList != null) {
			result.status = true;
			result.data = shopList;
		} else {
			result.status = false;
			result.msg = "업체가 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "업체 수정")
	@PutMapping("/{id}")
	private ResponseEntity<BasicResponse> update(@RequestBody ShopDto shopDto) {
		logger.debug("업체 수정");
		final BasicResponse result = new BasicResponse();
		if (shopService.update(shopDto)) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "업체 수정에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "업체 삭제")
	@DeleteMapping("/{id}")
	private ResponseEntity<BasicResponse> update(@PathVariable("id") int id) {
		logger.debug("업체 삭제");
		final BasicResponse result = new BasicResponse();
		if (shopService.delete(id)) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "업체 삭제에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 업체에 대한 상품 등록", response = BasicResponse.class)
	@PostMapping("/{id}/products")
	private ResponseEntity<BasicResponse> createProduct(@RequestBody ProductDto productDto) {
		logger.debug("해당 업체에 대한 상품 등록");
		final BasicResponse result = new BasicResponse();
		if (productService.create(productDto)) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "해당 업체에 대한 상품 등록에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 업체의 상품 리스트 조회", response = BasicResponse.class)
	@GetMapping("/{id}/products")
	private ResponseEntity<BasicResponse> readAllProduct(@PathVariable("id") int id) {
		logger.debug("해당 업체의 상품 리스트 조회");
		final BasicResponse result = new BasicResponse();
		List<ProductDto> productList = productService.readAll(id);
		if (productList != null) {
			result.status = true;
			result.data = productList;
		} else {
			result.status = false;
			result.msg = "해당 업체의 상품 리스트가 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
}
