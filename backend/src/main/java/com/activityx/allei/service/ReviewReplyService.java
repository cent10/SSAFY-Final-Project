package com.activityx.allei.service;

import com.activityx.allei.dto.ReviewReplyDto;

public interface ReviewReplyService {

	// 후기 답글 등록
	boolean create(ReviewReplyDto reviewDto);
}
