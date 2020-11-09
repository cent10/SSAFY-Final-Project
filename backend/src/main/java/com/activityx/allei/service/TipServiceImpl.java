package com.activityx.allei.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activityx.allei.dao.TipDao;
import com.activityx.allei.dto.TipDto;

@Service
public class TipServiceImpl implements TipService{
	
	@Autowired
	TipDao dao;

	@Override
	public boolean createTip(TipDto tip) {
		return dao.createTip(tip) == 1;
	}

	@Override
	public boolean modifyTip(TipDto tip) {
		return dao.modifyTip(tip) == 1;
	}

	@Override
	public boolean deleteTip(int id) {
		return dao.deleteTip(id) == 1;
	}

	@Override
	public TipDto detailTip(int id) {
		return dao.detailTip(id);
	}

	@Override
	public ArrayList<TipDto> allTips(int page, String keyword) {
		if(keyword == null) keyword = "";
		int start = 12*page;
		return dao.allTips(start, keyword);
	}
	
	@Override
	public boolean increaseHits(int id) {
		return dao.increaseHits(id) == 1;
	}
	
	@Override
	public ArrayList<TipDto> getTipsHasKeyword(String keyword) {
		if(keyword == null) keyword = "";
		return dao.getTipsHasKeyword(keyword);
	}

}
