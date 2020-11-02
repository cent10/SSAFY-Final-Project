package com.activityx.allei.service;

import java.util.List;

import com.activityx.allei.dto.NoticeDto;

public interface NoticeService {

	// 공지사항 등록
	boolean create(NoticeDto noticeDto);
	
	// 공지사항 상세보기
	NoticeDto read(int id);
	
	// 공지사항 리스트 조회
	List<NoticeDto> readAll();
	
	// 공지사항 검색 (제목으로 검색)
	List<NoticeDto> search(String searchword);
	
	// 공지사항 수정
	boolean update(NoticeDto noticeDto);
}
