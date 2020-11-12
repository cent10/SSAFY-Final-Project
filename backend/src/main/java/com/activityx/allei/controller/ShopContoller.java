package com.activityx.allei.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.activityx.allei.dto.BasicResponse;
import com.activityx.allei.dto.ProductDto;
import com.activityx.allei.dto.ShopBean;
import com.activityx.allei.dto.ShopDto;
import com.activityx.allei.service.FileUploadService;
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
	
	@Autowired
	FileUploadService fileUploadService;
	
	@ApiOperation(value = "업체 등록", response = BasicResponse.class)
	@PostMapping("")
	private ResponseEntity<BasicResponse> createShop(@RequestParam(value = "categoryName") String categoryName, MultipartFile imgFile, MultipartFile imgDescFile, @RequestBody ShopDto shopDto) {
		logger.debug("업체 등록");
		final BasicResponse result = new BasicResponse();
		if (shopService.create(shopDto, categoryName)) {
			if (imgFile != null) {
				try {
					fileUploadService.fileUpload(shopDto.getId(), imgFile, 0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (imgDescFile != null) {
				try {
					fileUploadService.fileUpload(shopDto.getId(), imgDescFile, 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			result.status = true;
		} else {
			result.status = false;
			result.msg = "업체 등록에 실패했습니다.";
		}
		
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "업체 상세 조회", response = BasicResponse.class)
	@GetMapping("/{id}")
	private ResponseEntity<BasicResponse> readShop(@PathVariable("id") int id) {
		logger.debug("업체 상세 조회");
		final BasicResponse result = new BasicResponse();
		Map<String, Object> map = shopService.read(id);
		if (map != null) {
			result.status = true;
			result.data = map;
		} else {
			result.status = false;
			result.msg = "해당 업체가 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "업체 리스트 조회", response = BasicResponse.class)
	@GetMapping("")
	private ResponseEntity<BasicResponse> readAllShops() {
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
	
//	@ApiOperation(value = "업체 검색", response = BasicResponse.class)
//	@GetMapping("/search/{searchword}")
//	private ResponseEntity<BasicResponse> searchShop(@PathVariable("searchword") String searchword) {
//		logger.debug("업체 검색");
//		final BasicResponse result = new BasicResponse();
//		Map<String, Object> map = shopService.search(searchword);
//		if (map != null) {
//			result.status = true;
//			result.data = map;
//		} else {
//			result.status = false;
//			result.msg = "업체가 없습니다.";
//		}
//		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
//	}
	
	@ApiOperation(value = "업체 검색 (레저 서비스 업체 (상위 12개), 장비 대여 업체 (상위 12개))", response = BasicResponse.class)
	@GetMapping("/search/{searchword}")
	private ResponseEntity<BasicResponse> searchShopLimit(@PathVariable("searchword") String searchword) {
		logger.debug("업체 검색 (레저 서비스 업체 (상위 12개), 장비 대여 업체 (상위 12개))");
		final BasicResponse result = new BasicResponse();
		Map<String, Object> map = shopService.searchLimit(searchword);
		if (map != null) {
			result.status = true;
			result.data = map;
		} else {
			result.status = false;
			result.msg = "업체가 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "레저 서비스 업체 상세 검색 ((num*12)번부터 12개)", response = BasicResponse.class)
	@GetMapping("/detailsearch/leisureshop")
	private ResponseEntity<BasicResponse> detailSearchLeisureShop(@RequestParam(value = "num") int num, //번호 0부터 시작
																@RequestParam(value = "minPrice", defaultValue = "0") int minPrice, //최저금액
																@RequestParam(value = "maxPrice", defaultValue = "99999999") int maxPrice, //최고금액 
																@RequestParam(value = "region", defaultValue = "") String region, //지역
																@RequestParam(value = "category", defaultValue = "") String category) { //카테고리
		logger.debug("레저 서비스 업체 상세 검색");
		final BasicResponse result = new BasicResponse();
		List<ShopBean> shopList = shopService.detailSearchLeisureShop(num, minPrice, maxPrice, region, category);
		if (shopList.size() > 0) {
			result.status = true;
			result.data = shopList;
		} else {
			result.status = false;
			result.msg = "해당되는 레저 서비스 업체들이 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "장비 대여 업체 상세 검색 ((num*12)번부터 12개)", response = BasicResponse.class)
	@GetMapping("/detailsearch/rentalshop")
	private ResponseEntity<BasicResponse> detailSearchRentalShop(@RequestParam(value = "num") int num,	//번호 0부터 시작
																@RequestParam(value = "minPrice", defaultValue = "0") int minPrice, //최저금액 
																@RequestParam(value = "maxPrice", defaultValue = "99999999") int maxPrice, //최고금액
																@RequestParam(value = "region", defaultValue = "") String region, //지역
																@RequestParam(value = "category", defaultValue = "") String category) { // 카테고리
		logger.debug("장비 대여 업체 상세 검색");
		final BasicResponse result = new BasicResponse();
		List<ShopBean> shopList = shopService.detailSearchRentalShop(num, minPrice, maxPrice, region, category);
		if (shopList.size() > 0) {
			result.status = true;
			result.data = shopList;
		} else {
			result.status = false;
			result.msg = "해당되는 장비 대여 업체들이 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "레저 서비스 업체 상세 검색 (가격 낮은 순) ((num*12)번부터 12개)", response = BasicResponse.class)
	@GetMapping("/detailsearch/leisureshop/orderbyprice")
	private ResponseEntity<BasicResponse> detailSearchLeisureShopOrderByPrice(@RequestParam(value = "num") int num, //번호 0부터 시작
																			@RequestParam(value = "minPrice", defaultValue = "0") int minPrice, //최저금액
																			@RequestParam(value = "maxPrice", defaultValue = "99999999") int maxPrice, //최고금액
																			@RequestParam(value = "region", defaultValue = "") String region, //지역
																			@RequestParam(value = "category", defaultValue = "") String category) { //카테고리
		logger.debug("레저 서비스 업체 상세 검색 (가격 낮은 순)");
		final BasicResponse result = new BasicResponse();
		List<ShopBean> shopList = shopService.detailSearchLeisureShopOrderByPrice(num, minPrice, maxPrice, region, category);
		if (shopList.size() > 0) {
			result.status = true;
			result.data = shopList;
		} else {
			result.status = false;
			result.msg = "해당되는 레저 서비스 업체들이 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "장비 대여 업체 상세 검색 (가격 낮은 순) ((num*12)번부터 12개)", response = BasicResponse.class)
	@GetMapping("/detailsearch/rentalshop/orderbyprice")
	private ResponseEntity<BasicResponse> detailSearchRentalShopOrderByPrice(@RequestParam(value = "num") int num, //번호 0부터 시작
																			@RequestParam(value = "minPrice", defaultValue = "0") int minPrice, //최저금액
																			@RequestParam(value = "maxPrice", defaultValue = "99999999") int maxPrice, //최고금액
																			@RequestParam(value = "region", defaultValue = "") String region, //지역
																			@RequestParam(value = "category", defaultValue = "") String category) { //카테고리
		logger.debug("장비 대여 업체 상세 검색 (가격 낮은 순)");
		final BasicResponse result = new BasicResponse();
		List<ShopBean> shopList = shopService.detailSearchRentalShopOrderByPrice(num, minPrice, maxPrice, region, category);
		if (shopList.size() > 0) {
			result.status = true;
			result.data = shopList;
		} else {
			result.status = false;
			result.msg = "해당되는 장비 대여 업체들이 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "레저 서비스 업체 상세 검색 (평점 높은 순) ((num*12)번부터 12개)", response = BasicResponse.class)
	@GetMapping("/detailsearch/leisureshop/orderbyrate")
	private ResponseEntity<BasicResponse> detailSearchLeisureShopOrderByRate(@RequestParam(value = "num") int num, //번호 0부터 시작
																			@RequestParam(value = "minPrice", defaultValue = "0") int minPrice, //최저금액
																			@RequestParam(value = "maxPrice", defaultValue = "99999999") int maxPrice, //최고금액
																			@RequestParam(value = "region", defaultValue = "") String region, //지역
																			@RequestParam(value = "category", defaultValue = "") String category) { //카테고리
		logger.debug("레저 서비스 업체 상세 검색 (평점 높은 순)");
		final BasicResponse result = new BasicResponse();
		List<ShopBean> shopList = shopService.detailSearchLeisureShopOrderByRate(num, minPrice, maxPrice, region, category);
		if (shopList.size() > 0) {
			result.status = true;
			result.data = shopList;
		} else {
			result.status = false;
			result.msg = "해당되는 레저 서비스 업체들이 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "장비 대여 업체 상세 검색 (평점 높은 순) ((num*12)번부터 12개)", response = BasicResponse.class)
	@GetMapping("/detailsearch/rentalshop/orderbyrate")
	private ResponseEntity<BasicResponse> detailSearchRentalShopOrderByRate(@RequestParam(value = "num") int num, //번호 0부터 시작
																			@RequestParam(value = "minPrice", defaultValue = "0") int minPrice, //최저금액
																			@RequestParam(value = "maxPrice", defaultValue = "99999999") int maxPrice, //최고금액
																			@RequestParam(value = "region", defaultValue = "") String region, //지역
																			@RequestParam(value = "category", defaultValue = "") String category) { //카테고리
		logger.debug("장비 대여 업체 상세 검색 (평점 높은 순)");
		final BasicResponse result = new BasicResponse();
		List<ShopBean> shopList = shopService.detailSearchRentalShopOrderByRate(num, minPrice, maxPrice, region, category);
		if (shopList.size() > 0) {
			result.status = true;
			result.data = shopList;
		} else {
			result.status = false;
			result.msg = "해당되는 장비 대여 업체들이 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "업체 수정", response = BasicResponse.class)
	@PutMapping("/{id}")
	private ResponseEntity<BasicResponse> updateShop(@RequestBody ShopDto shopDto) {
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
	
	@ApiOperation(value = "업체 삭제", response = BasicResponse.class)
	@DeleteMapping("/{id}")
	private ResponseEntity<BasicResponse> deleteShop(@PathVariable("id") int id) {
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
	@PostMapping("/{shop}/products")
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
	@GetMapping("/{shop}/products")
	private ResponseEntity<BasicResponse> readAllProducts(@PathVariable("shop") int shop) {
		logger.debug("해당 업체의 상품 리스트 조회");
		final BasicResponse result = new BasicResponse();
		List<ProductDto> productList = productService.readAll(shop);
		if (productList != null) {
			result.status = true;
			result.data = productList;
		} else {
			result.status = false;
			result.msg = "해당 업체의 상품 리스트가 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "상품 수정", response = BasicResponse.class)
	@PutMapping("/{shop}/products/{id}")
	private ResponseEntity<BasicResponse> updateProduct(@RequestBody ProductDto productDto) {
		logger.debug("상품 수정");
		final BasicResponse result = new BasicResponse();
		if (productService.update(productDto)) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "상품 수정에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "상품 삭제", response = BasicResponse.class)
	@DeleteMapping("/{shop}/products/{id}")
	private ResponseEntity<BasicResponse> deleteProduct(@PathVariable("id") int id) {
		logger.debug("상품 삭제");
		final BasicResponse result = new BasicResponse();
		if (productService.delete(id)) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "상품 삭제에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "업체 img 파일 업로드", response = String.class)
	@PutMapping("/{id}/fileupload/img")
	private ResponseEntity<BasicResponse> uploadImg(@PathVariable("id") int id, MultipartFile file) throws Exception {
		logger.debug("업체 img 파일 업로드");
		final BasicResponse result = new BasicResponse();
		if (fileUploadService.fileUpload(id, file, 0) > 0) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "업체 img 파일 업로드에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "업체 imgDesc 파일 업로드", response = String.class)
	@PutMapping("/{id}/fileupload/imgdesc")
	private ResponseEntity<BasicResponse> uploadImgDesc(@PathVariable("id") int id, MultipartFile file) throws Exception {
		logger.debug("업체 imgDesc 파일 업로드");
		final BasicResponse result = new BasicResponse();
		if (fileUploadService.fileUpload(id, file, 1) > 0) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "업체 imgDesc 파일 업로드에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자 아이디로 업체 아이디 조회하기", response = BasicResponse.class)
	@GetMapping("find/{id}")
	private ResponseEntity<BasicResponse> getShopIdByUser(@PathVariable("id") int id) {
		logger.debug("사용자 아이디로 업체 아이디 조회하기");
		final BasicResponse result = new BasicResponse();
		Integer data = shopService.getShopIdByUser(id);
		if (data != null) {
			result.status = true;
			result.data = data;
		} else {
			result.status = false;
			result.msg = "해당 업체가 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}

}
