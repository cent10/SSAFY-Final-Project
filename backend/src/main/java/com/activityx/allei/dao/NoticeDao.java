package com.activityx.allei.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.activityx.allei.dto.NoticeDto;

@Mapper
public interface NoticeDao {

	// 공지사항 등록
	public int create(NoticeDto noticeDto);
	
	// 공지사항 상세보기
	public NoticeDto read(int id);
	
	// 공지사항 리스트 조회
	public List<NoticeDto> readAll();
	
	// 공지사항 검색 (제목으로 검색)
	public List<NoticeDto> search(String searchword);
	
	// 공지사항 수정
	public int update(NoticeDto noticeDto);
	
	// 조회수 증가
	public int increaseHits(int id);
}
