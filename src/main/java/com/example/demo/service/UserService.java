/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;

/**
 * 
 */
@Service
public class UserService {
	@Autowired
	private UserRepository repository;

	public User saveUser(UserRequest userRequest) {
		User user = new User();
		user.setAge(userRequest.getAge());
		user.setEmail(userRequest.getEmail());
		user.setGender(userRequest.getGender());
		user.setMobile(userRequest.getMobile());
		user.setName(userRequest.getName());
		user.setNationality(userRequest.getNationality());
		return repository.save(user);

	}

	public User getUser(int id) throws UserNotFoundException {
		User user = repository.findByUserId(id);
		if (user != null) {
			return user;
		} else {
			throw new UserNotFoundException("user not found with id : " + id);
		}
	}

	public List<User> getAllUsers() {
		return repository.findAll();
	}
}