package com.activityx.allei.controller;

import java.util.ArrayList;

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

import com.activityx.allei.dto.BasicResponse;
import com.activityx.allei.dto.TipDto;
import com.activityx.allei.dto.TipReplyDto;
import com.activityx.allei.service.TipReplyService;
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
	
	@Autowired
	TipReplyService replyService;
	
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
	
	@ApiOperation(value = "팁 게시글 수정", response = BasicResponse.class)
	@PutMapping("modify")
	public ResponseEntity<BasicResponse> modifyTip(@RequestBody TipDto tip) {
		logger.debug("Tip Board test : modifyTip - 호츌");
		final BasicResponse result = new BasicResponse();
		if(service.modifyTip(tip)){
			result.status = true;
		}else{
			result.status = false;
			result.msg = "팁 게시글 수정에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "팁 게시글 삭제", response = BasicResponse.class)
	@DeleteMapping("delete/{id}")
	public ResponseEntity<BasicResponse> deleteTip(@PathVariable int id) {
		logger.debug("Tip Board test : deleteTip - 호츌");
		final BasicResponse result = new BasicResponse();
		if(service.deleteTip(id)){
			result.status = true;
		}else{
			result.status = false;
			result.msg = "팁 게시글을 삭제하지 못했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "팁 게시글 상세보기", response = BasicResponse.class)
	@GetMapping("detail/{id}")
	public ResponseEntity<BasicResponse> detailTip(@PathVariable int id) {
		logger.debug("Tip Board test : detailTip - 호츌");
		final BasicResponse result = new BasicResponse();
		TipDto data = service.detailTip(id);
		if(data != null){
			result.status = true;
			result.data = data;
		}else{
			result.status = false;
			result.msg = "해당 게시글이 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "모든 게시글을 가져 옵니다.", response = BasicResponse.class)
	@GetMapping("all")
	public ResponseEntity<BasicResponse> allTips(@RequestParam(required = false) String keyword) {
		logger.debug("Tip Board test : allTips - 호츌");
		final BasicResponse result = new BasicResponse();
		ArrayList<TipDto> data = service.allTips(keyword);
		if(data != null){
			result.status = true;
			result.data = data;
		}else{
			result.status = false;
			result.msg = "게시글이 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "특정 게시글 조회수 증가", response = BasicResponse.class)
	@GetMapping("increase/{id}")
	public ResponseEntity<BasicResponse> increaseHits(@PathVariable int id) {
		logger.debug("Tip Board test : increaseHits - 호츌");
		final BasicResponse result = new BasicResponse();
		if(service.increaseHits(id)){
			result.status = true;
		}else{
			result.status = false;
			result.msg = "조회수 수정에 실패 했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "통합 검색을 합니다.", response = BasicResponse.class)
	@GetMapping("total")
	public ResponseEntity<BasicResponse> getTipsHasKeyword(@RequestParam(required = false) String keyword) {
		logger.debug("Tip Board test : getTipsHasKeyword - 호츌");
		final BasicResponse result = new BasicResponse();
		ArrayList<TipDto> data = service.getTipsHasKeyword(keyword);
		if(data != null){
			result.status = true;
			result.data = data;
		}else{
			result.status = false;
			result.msg = "게시글이 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "특정 글에 대한 모든 댓글을 가져 옵니다.", response = BasicResponse.class)
	@GetMapping("reply/all/{tipId}")
	public ResponseEntity<BasicResponse> allReplies(@PathVariable int tipId) {
		logger.debug("Tip Reply test : getAllReplies - 호츌");
		final BasicResponse result = new BasicResponse();
		ArrayList<TipReplyDto> data = replyService.getAllReplies(tipId);
		if(data != null){
			result.status = true;
			result.data = data;
		}else{
			result.status = false;
			result.msg = "댓글이 없습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "댓글을 작성 합니다.", response = BasicResponse.class)
	@PostMapping("reply/add")
	public ResponseEntity<BasicResponse> addReply(@RequestBody TipReplyDto tipReply) {
		logger.debug("Tip Reply test : addReply - 호츌");
		final BasicResponse result = new BasicResponse();
		if(replyService.addReply(tipReply)){
			result.status = true;
		}else{
			result.status = false;
			result.msg = "댓글이 작성에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "댓글을 작성 합니다.", response = BasicResponse.class)
	@PutMapping("reply/modify")
	public ResponseEntity<BasicResponse> modifyReply(@RequestBody TipReplyDto tipReply) {
		logger.debug("Tip Reply test : modifyReply - 호츌");
		final BasicResponse result = new BasicResponse();
		if(replyService.modifyReply(tipReply)){
			result.status = true;
		}else{
			result.status = false;
			result.msg = "댓글이 수정에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "댓글을 작성 합니다.", response = BasicResponse.class)
	@DeleteMapping("reply/delete/{id}")
	public ResponseEntity<BasicResponse> deleteReply(@PathVariable int id) {
		logger.debug("Tip Reply test : deleteReply - 호츌");
		final BasicResponse result = new BasicResponse();
		if(replyService.deleteReply(id)){
			result.status = true;
		}else{
			result.status = false;
			result.msg = "댓글이 삭제에 실패했습니다.";
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
}
