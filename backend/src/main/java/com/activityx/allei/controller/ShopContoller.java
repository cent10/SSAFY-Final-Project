package com.activityx.allei.controller;

import java.util.List;

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

import com.activityx.allei.dto.ShopDto;
import com.activityx.allei.service.ShopService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/activityx/shops")
public class ShopContoller {
	@Autowired
	ShopService shopService;
	
	@ApiOperation(value = "업체 등록", response = ShopDto.class)
	@PostMapping("")
	private ResponseEntity<?> create(@RequestBody ShopDto shopDto) {
		shopService.create(shopDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "업체 상세 조회", response = ShopDto.class)
	@GetMapping("/{id}")
	private ResponseEntity<ShopDto> read(@PathVariable("id") int id) {
		ShopDto shop = shopService.read(id);
		return new ResponseEntity<ShopDto>(shop, HttpStatus.OK);
	}
	
	@ApiOperation(value = "업체 리스트 조회", response = ShopDto.class)
	@GetMapping("")
	private ResponseEntity<List<ShopDto>> readAll() {
		List<ShopDto> shopList = shopService.readAll();
		return new ResponseEntity<List<ShopDto>>(shopList, HttpStatus.OK);
	}
	
	@ApiOperation(value = "업체 검색", response = ShopDto.class)
	@GetMapping("/search/{searchword}")
	private ResponseEntity<List<ShopDto>> search(@PathVariable("searchword") String searchword) {
		List<ShopDto> shopList = shopService.search(searchword);
		return new ResponseEntity<List<ShopDto>>(shopList, HttpStatus.OK);
	}
	
	@ApiOperation(value = "업체 수정", response = ShopDto.class)
	@PutMapping("/{id}")
	private ResponseEntity<?> update(@RequestBody ShopDto shopDto) {
		shopService.update(shopDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
