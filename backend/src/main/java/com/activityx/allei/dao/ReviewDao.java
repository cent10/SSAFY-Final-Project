package com.activityx.allei.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.activityx.allei.dto.ReviewDto;

@Mapper
public interface ReviewDao {

	// 후기 등록
	public int create(ReviewDto reviewDto);
	
	// 후기 조회
	public ReviewDto read(int id);
	
	// 후기 리스트 조회 (업체 번호로 후기 리스트 조회)
	public List<ReviewDto> readReviews(int shop);
	
	// 후기 수정
	public int update(ReviewDto reviewDto);
	
	// 후기 삭제
	public int delete(int id);
	
	// 후기 작성 여부
	public int reviewWrited(int reservation);
}
