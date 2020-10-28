package com.activityx.allei.service;

import com.activityx.allei.dto.ReviewReplyDto;

public interface ReviewReplyService {

	// 후기 답글 등록
	boolean create(ReviewReplyDto reviewDto);
	
	// 후기 답글 조회
	ReviewReplyDto read(int review);
	
	// 후기 답글 수정
	boolean update(ReviewReplyDto reviewDto);
	
	// 후기 답글 삭제
	boolean delete(int id);
}
