package com.activityx.allei.service;

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

}