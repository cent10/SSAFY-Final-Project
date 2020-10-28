package com.activityx.allei.dao;

import org.apache.ibatis.annotations.Mapper;

import com.activityx.allei.dto.ReviewReplyDto;

@Mapper
public interface ReviewReplyDao {

	// 후기 답글 등록
	public int create(ReviewReplyDto reviewReplyDto);
	
	// 후기 답글 조회
	public ReviewReplyDto read(int review);
	
	// 후기 답글 수정
	public int update(ReviewReplyDto reviewReplyDto);
	
	// 후기 답글 삭제
	public int delete(int id);
}
