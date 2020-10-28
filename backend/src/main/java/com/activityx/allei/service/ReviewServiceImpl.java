package com.activityx.allei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activityx.allei.dao.ReviewDao;
<<<<<<< HEAD
import com.activityx.allei.dto.ReviewDto;
=======
>>>>>>> 4a47f71a0c22b591e8d3bed8ab36df9b3f7cd6a9

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewDao reviewDao;
<<<<<<< HEAD

	@Override
	public boolean create(ReviewDto reviewDto) {
		return reviewDao.create(reviewDto) == 1;
	}

	@Override
	public ReviewDto read(int id) {
		return reviewDao.read(id);
	}

	@Override
	public boolean update(ReviewDto reviewDto) {
		return reviewDao.update(reviewDto) == 1;
	}

	@Override
	public boolean delete(int id) {
		return reviewDao.delete(id) == 1;
	}
=======
>>>>>>> 4a47f71a0c22b591e8d3bed8ab36df9b3f7cd6a9
	
	
}
