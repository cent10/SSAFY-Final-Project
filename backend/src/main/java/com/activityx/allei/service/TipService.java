package com.activityx.allei.service;

import java.util.ArrayList;

import com.activityx.allei.dto.TipDto;

public interface TipService {

	//게시글 쓰기
	boolean createTip(TipDto tip);
	//게시글 수정
	boolean modifyTip(TipDto tip);
	//게시글 삭제
	boolean deleteTip(int id);
	//게시글 상세보기
	TipDto detailTip(int id);
	//전체 게시글 목록
	ArrayList<TipDto> allTips(String keyword);
	//조회수 증가
	boolean increaseHits(int id);
	//통합검색
	ArrayList<TipDto> getTipsHasKeyword(String keyword);

}
