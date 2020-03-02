package com.cts.training.stockview.userservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.cts.training.stockview.userservice.entity.UserEntity;
import com.cts.training.stockview.userservice.model.User;
import com.cts.training.stockview.userservice.repo.UserRepository;
import com.cts.training.stockview.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	JavaMailSender javaMailSender;

	@Override
	public List<User> getAllUsers() {
		List<UserEntity> entities = userRepo.findAll();
		List<User> usersDTO = new ArrayList<User>();
		for (UserEntity entity : entities) {
			User userDTO = new User();
			BeanUtils.copyProperties(entity, userDTO);
			usersDTO.add(userDTO);
		}
		System.out.println("Entity: " + entities);
		System.out.println("DTO: " + usersDTO);
		return usersDTO;
	}

	@Override
	public User getUserById(int id) throws NoSuchElementException {
		Optional<UserEntity> user = userRepo.findById(id);
		User userDTO = new User();
		BeanUtils.copyProperties(user.get(), userDTO);
		return userDTO;
	}

	@Override
	public User getUserByEmail(String email) throws NoSuchElementException {
		UserEntity user = userRepo.findByEmail(email).get();
		User userDTO = new User();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;

	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) throws NoSuchElementException {
		UserEntity user = userRepo.findByUsernameAndPassword(username, password).get();
		User userDTO = new User();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}

	@Override
	public User addUser(User user) {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		double randomNum = Math.random();
		
		UserEntity userObj = userRepo.save(userEntity);
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setFrom("gajetlvr@gmail.com");
			helper.setTo(userObj.getEmail());
			helper.setSubject("Testing Mail");
			helper.setText("Account created <a href='http://localhost:4200/user/activate?" + userObj.getEmail()
					+ "'> click </a> here", true);

			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		BeanUtils.copyProperties(userObj, user);
		return user;
	}

	@Override
	public boolean activateUser(String email) {
		System.out.println(email);
		UserEntity user = userRepo.findByEmail(email).orElse(new UserEntity());
		System.out.println(user);
		if (!user.isConfirmed()) {
			System.out.println(user);
			user.setConfirmed(true);
			userRepo.save(user);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public User updateUser(User user) {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		BeanUtils.copyProperties(userRepo.save(userEntity), user);
		return user;
	}

	@Override
	public void deleteUser(int id) {
		userRepo.deleteById(id);
	}
}
