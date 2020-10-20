package com.activityx.allei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activityx.allei.dao.SampleDao;
import com.activityx.allei.dto.SampleDto;

@Service
public class SampleServiceImpl implements SampleService {
	
	@Autowired
	SampleDao dao;
	
	@Override
	public SampleDto sample(int n) {
		SampleDto result = null;
		if(n%2 == 0)
			result = new SampleDto();
			
		return result;
	}

}
