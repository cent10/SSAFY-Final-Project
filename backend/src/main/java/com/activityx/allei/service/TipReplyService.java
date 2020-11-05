package com.activityx.allei.service;

import java.util.ArrayList;

import com.activityx.allei.dto.TipReplyDto;

public interface TipReplyService {

	//특정 글에 대한 모든 댓글 가져오기
	ArrayList<TipReplyDto> getAllReplies(int tipId);
	
	//댓글 상세보기
	TipReplyDto getReply(int id);
	
	//댓글 쓰기
	boolean addReply(TipReplyDto tipReply);
	
	//댓글 수정
	boolean modifyReply(TipReplyDto tipReply);
	
	//댓글 삭제
	boolean deleteReply(int id);
		
}
