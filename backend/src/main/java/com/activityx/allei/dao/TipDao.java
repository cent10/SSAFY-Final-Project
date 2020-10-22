package com.activityx.allei.dao;

import com.activityx.allei.dto.TipDto;

public interface TipDao {
	
	//게시글 쓰기
	int createTip(TipDto tip);
	
	//게시글 수정
	int modifyTip(TipDto tip);
	
	//게시글 삭제
	//게시글 상세보기
	//전체 게시글 목록
	//조회수 증가

}
