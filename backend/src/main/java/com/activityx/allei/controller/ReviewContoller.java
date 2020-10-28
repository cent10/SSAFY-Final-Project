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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activityx.allei.dto.BasicResponse;
import com.activityx.allei.dto.ReviewDto;
import com.activityx.allei.dto.ReviewReplyDto;
import com.activityx.allei.service.ReviewReplyService;
import com.activityx.allei.service.ReviewService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/reviews")
public class ReviewContoller {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ReviewReplyService reviewReplyService;
	
	@ApiOperation(value = "후기 작성", response = BasicResponse.class)
	@PostMapping("")
	private ResponseEntity<BasicResponse> createReview(@RequestBody ReviewDto reviewDto) {
		logger.debug("후기 작성");
		final BasicResponse result = new BasicResponse();
		if (reviewService.create(reviewDto)) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "후기 작성에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "후기 조회", response = BasicResponse.class)
	@GetMapping("/{id}")
	private ResponseEntity<BasicResponse> readReview(@PathVariable("id") int id) {
		logger.debug("후기 조회");
		final BasicResponse result = new BasicResponse();
		ReviewDto reviewDto = reviewService.read(id);
		if (reviewDto != null) {
			result.status = true;
			result.data = reviewDto;
		} else {
			result.status = false;
			result.msg = "해당 후기가 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "후기 수정", response = BasicResponse.class)
	@PutMapping("/{id}")
	private ResponseEntity<BasicResponse> updateReview(@RequestBody ReviewDto reviewDto) {
		logger.debug("후기 수정");
		final BasicResponse result = new BasicResponse();
		if (reviewService.update(reviewDto)) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "후기 수정에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "후기 삭제", response = BasicResponse.class)
	@DeleteMapping("/{id}")
	private ResponseEntity<BasicResponse> deleteReview(@PathVariable("id") int id) {
		logger.debug("후기 삭제");
		final BasicResponse result = new BasicResponse();
		if (reviewService.delete(id)) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "후기 삭제에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "후기 답글 작성", response = BasicResponse.class)
	@PostMapping("/{id}/replies")
	private ResponseEntity<BasicResponse> createReviewReply(@RequestBody ReviewReplyDto reviewReplyDto) {
		logger.debug("후기 답글 작성");
		final BasicResponse result = new BasicResponse();
		if (reviewReplyService.create(reviewReplyDto)) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "후기 답글 작성에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "후기 답글 조회", response = BasicResponse.class)
	@GetMapping("/{review}/replies/{id}")
	private ResponseEntity<BasicResponse> readReviewReply(@PathVariable("review") int review) {
		logger.debug("후기 답글 조회");
		final BasicResponse result = new BasicResponse();
		ReviewReplyDto reviewReplyDto = reviewReplyService.read(review);
		if (reviewReplyDto != null) {
			result.status = true;
			result.data = reviewReplyDto;
		} else {
			result.status = false;
			result.msg = "해당 후기의 답글이 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "후기 답글 수정", response = BasicResponse.class)
	@PutMapping("/{id}/replies")
	private ResponseEntity<BasicResponse> updateReviewReply(@RequestBody ReviewReplyDto reviewReplyDto) {
		logger.debug("후기 답글 수정");
		final BasicResponse result = new BasicResponse();
		if (reviewReplyService.update(reviewReplyDto)) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "후기 답글 수정에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "후기 답글 삭제", response = BasicResponse.class)
	@DeleteMapping("/{id}/replies")
	private ResponseEntity<BasicResponse> deleteReviewReply(@PathVariable("id") int id) {
		logger.debug("후기 답글 삭제");
		final BasicResponse result = new BasicResponse();
		if (reviewReplyService.delete(id)) {
			result.status = true;
		} else {
			result.status = false;
			result.msg = "후기 답글 삭제에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
}
