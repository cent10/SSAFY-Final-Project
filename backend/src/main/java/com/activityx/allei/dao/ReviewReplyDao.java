package com.activityx.allei.dao;

import org.apache.ibatis.annotations.Mapper;

import com.activityx.allei.dto.ReviewReplyDto;

@Mapper
public interface ReviewReplyDao {

	// 후기 답글 등록
	public int create(ReviewReplyDto reviewReplyDto);
	
}
