package com.activityx.allei.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.activityx.allei.dto.TipReplyDto;

@Mapper
public interface TipReplyDao {

	//특정 글에 대한 모든 댓글 가져오기
	ArrayList<TipReplyDto> getAllReplies(int tipId);
	
	//댓글 쓰기
	int addReply(TipReplyDto tipReply);
	
	//댓글 수정
	int modifyReply(TipReplyDto tipReply);
	
	//댓글 삭제
	int deleteReply(int id);

}
