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

	@Override
	public ReviewReplyDto read(int review) {
		return reviewReplyDao.read(review);
	}

	@Override
	public boolean update(ReviewReplyDto reviewReplyDto) {
		return reviewReplyDao.update(reviewReplyDto) == 1;
	}

	@Override
	public boolean delete(int id) {
		return reviewReplyDao.delete(id) == 1;
	}

	
}
