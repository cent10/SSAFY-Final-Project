package com.activityx.allei.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.activityx.allei.dto.TipDto;

@Mapper
public interface TipDao {
	
	//게시글 쓰기
	int createTip(TipDto tip);
	
	//게시글 수정
	int modifyTip(TipDto tip);
	
	//게시글 삭제
	int deleteTip(int id);
	
	//게시글 상세보기
	TipDto detailTip(int id);
	
	//전체 게시글 목록
	ArrayList<TipDto> allTips(int start, String keyword);
	
	//조회수 증가
	int increaseHits(int id);

	//통합검색
	ArrayList<TipDto> getTipsHasKeyword(String keyword);
	
}
