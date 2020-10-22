package com.activityx.allei.service;

import com.activityx.allei.dto.TipDto;

public interface TipService {

	//게시글 쓰기
	boolean createTip(TipDto tip);
	//게시글 수정
	boolean modifyTip(TipDto tip);
	//게시글 삭제
	//게시글 상세보기
	//전체 게시글 목록
	//조회수 증가

}
