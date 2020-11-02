package com.activityx.allei.service;

import java.util.List;

import com.activityx.allei.dto.NoticeDto;

public interface NoticeService {

	// 공지사항 등록
	boolean create(NoticeDto noticeDto);
}
