package com.activityx.allei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activityx.allei.dao.UserDao;
import com.activityx.allei.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao dao;

	@Override
	public boolean joinUser(User user) {
		return dao.create(user) == 1;
	}

	@Override
	public boolean deleteUser(int id) {
		return dao.delete(id) == 1;
	}

	@Override
	public List<User> findAll() {
		return dao.readAll();
	}

	@Override
	public User findById(int id) {
		return dao.findById(id);
	}

	@Override
	public User findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public List<User> findlistByName(String name) {
		return dao.findlistByName(name);
	}

	@Override
	public User findByEmail(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public List<User> findlistByEmail(String email) {
		return dao.findlistByEmail(email);
	}

	@Override
	public User findByKey(long key) {
		return dao.findByKey(key);
	}

	@Override
	public boolean modifyUser(User user) {
		return dao.update(user) == 1;
	}

}
