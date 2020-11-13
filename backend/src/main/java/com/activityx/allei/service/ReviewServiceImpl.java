package com.activityx.allei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activityx.allei.dao.ReviewDao;
import com.activityx.allei.dto.ReviewDto;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewDao reviewDao;

	@Override
	public boolean create(ReviewDto reviewDto) {
		return reviewDao.create(reviewDto) == 1;
	}

	@Override
	public ReviewDto read(int id) {
		return reviewDao.read(id);
	}
	
	@Override
	public List<ReviewDto> readReviews(int shop) {
		return reviewDao.readReviews(shop);
	}

	@Override
	public boolean update(ReviewDto reviewDto) {
		return reviewDao.update(reviewDto) == 1;
	}

	@Override
	public boolean delete(int id) {
		return reviewDao.delete(id) == 1;
	}
	
}
