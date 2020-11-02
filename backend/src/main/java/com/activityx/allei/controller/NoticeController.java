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
import com.activityx.allei.dto.NoticeDto;
import com.activityx.allei.service.NoticeService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/notices")
public class NoticeController {
	
private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@Autowired
	NoticeService noticeService;
	
	@ApiOperation(value = "공지사항 등록", response = BasicResponse.class)
	@PostMapping("")
	private ResponseEntity<BasicResponse> createNotice(@RequestBody NoticeDto noticeDto) {
		logger.debug("공지사항 등록");
		final BasicResponse result = new BasicResponse();
		if (noticeService.create(noticeDto)) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "공지사항 등록에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 상세보기", response = BasicResponse.class)
	@GetMapping("/{id}")
	private ResponseEntity<BasicResponse> readNotice(@PathVariable("id") int id) {
		logger.debug("공지사항 상세보기");
		final BasicResponse result = new BasicResponse();
		NoticeDto noticeDto = noticeService.read(id);
		if (noticeDto != null) {
			result.status = true;
			result.data = noticeDto;
		} else {
			result.status = false;
			result.msg = "해당 공지사항이 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 리스트 조회", response = BasicResponse.class)
	@GetMapping("")
	private ResponseEntity<BasicResponse> readAllNotices() {
		logger.debug("공지사항 리스트 조회");
		final BasicResponse result = new BasicResponse();
		List<NoticeDto> noticeList = noticeService.readAll();
		if (noticeList != null) {
			result.status = true;
			result.data = noticeList;
		} else {
			result.status = false;
			result.msg = "공지사항이 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 검색 (제목으로 검색)", response = BasicResponse.class)
	@GetMapping("/search/{searchword}")
	private ResponseEntity<BasicResponse> searchNotice(@PathVariable("searchword") String searchword) {
		logger.debug("공지사항 검색 (제목으로 검색)");
		final BasicResponse result = new BasicResponse();
		List<NoticeDto> noticeList = noticeService.search(searchword);
		if (noticeList != null) {
			result.status = true;
			result.data = noticeList;
		} else {
			result.status = false;
			result.msg = "검색된 공지사항이 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 수정", response = BasicResponse.class)
	@PutMapping("/{id}")
	private ResponseEntity<BasicResponse> updateNotice(@RequestBody NoticeDto noticeDto) {
		logger.debug("공지사항 수정");
		final BasicResponse result = new BasicResponse();
		if (noticeService.update(noticeDto)) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "공지사항 수정에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 삭제", response = BasicResponse.class)
	@DeleteMapping("/{id}")
	private ResponseEntity<BasicResponse> deleteNotice(@PathVariable("id") int id) {
		logger.debug("공지사항 삭제");
		final BasicResponse result = new BasicResponse();
		if (noticeService.delete(id)) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "공지사항 삭제에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
}
