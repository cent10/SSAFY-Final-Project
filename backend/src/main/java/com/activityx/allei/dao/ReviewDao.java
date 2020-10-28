package com.activityx.allei.dao;

import org.apache.ibatis.annotations.Mapper;

<<<<<<< HEAD
import com.activityx.allei.dto.ReviewDto;

@Mapper
public interface ReviewDao {

	// 후기 등록
	public int create(ReviewDto reviewDto);
	
	// 후기 조회
	public ReviewDto read(int id);
	
	// 후기 수정
	public int update(ReviewDto reviewDto);
	
	// 후기 삭제
	public int delete(int id);
=======
@Mapper
public interface ReviewDao {

>>>>>>> 4a47f71a0c22b591e8d3bed8ab36df9b3f7cd6a9
}
