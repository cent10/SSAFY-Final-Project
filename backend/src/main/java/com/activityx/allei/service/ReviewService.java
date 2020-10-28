package com.activityx.allei.service;

import com.activityx.allei.dto.ReviewDto;

public interface ReviewService {

	// 후기 등록
	boolean create(ReviewDto reviewDto);
	
	// 후기 조회
	ReviewDto read(int id);
	
	// 후기 수정
	boolean update(ReviewDto reviewDto);
	
	// 후기 삭제
	boolean delete(int id);
}
