package com.activityx.allei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activityx.allei.dao.ReviewReplyDao;
import com.activityx.allei.dto.ReviewDto;
import com.activityx.allei.dto.ReviewReplyDto;

@Service
public class ReviewReplyServiceImpl implements ReviewReplyService {

	@Autowired
	ReviewReplyDao reviewReplyDao;

	@Override
	public boolean create(ReviewReplyDto reviewReplyDto) {
		return reviewReplyDao.create(reviewReplyDto) == 1;
	}

	
}
