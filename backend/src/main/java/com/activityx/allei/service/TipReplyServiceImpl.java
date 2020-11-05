package com.activityx.allei.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activityx.allei.dao.TipReplyDao;
import com.activityx.allei.dto.TipReplyDto;

@Service
public class TipReplyServiceImpl implements TipReplyService {
	
	@Autowired
	TipReplyDao dao;

	@Override
	public ArrayList<TipReplyDto> getAllReplies(int tipId) {
		return dao.getAllReplies(tipId);
	}
	
	@Override
	public TipReplyDto getReply(int id) {
		return dao.getReply(id);
	}

	@Override
	public boolean addReply(TipReplyDto tipReply) {
		return dao.addReply(tipReply) == 1;
	}

	@Override
	public boolean modifyReply(TipReplyDto tipReply) {
		return dao.modifyReply(tipReply) == 1;
	}

	@Override
	public boolean deleteReply(int id) {
		return dao.deleteReply(id) == 1;
	}

}
