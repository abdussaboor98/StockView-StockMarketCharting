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
import com.cts.training.stockview.userservice.model.PasswordChangeRequest;
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
	public boolean emailExists(String email) throws NoSuchElementException {
		return userRepo.findByEmail(email).isPresent();

	}
	
	@Override
	public boolean usernameExists(String email) throws NoSuchElementException {
		return userRepo.findByUsername(email).isPresent();	
	}
	
	@Override
	public Boolean isUserActive(String username) {
		return userRepo.isUserActive(username);
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) throws NoSuchElementException {
		UserEntity user = userRepo.findByUsernameAndPassword(username, password).get();
		User userDTO = new User();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}
	
	@Override
	public User getUserByUsername(String username) throws NoSuchElementException {
		UserEntity user = userRepo.findByUsername(username).get();
		User userDTO = new User();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}

	@Override
	public User addUser(User user) {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		userEntity.setUserType("ROLE_USER");
		userEntity.setConfirmed(false);
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
	public boolean activateUser(String email) throws NoSuchElementException {
		UserEntity user = userRepo.findByEmail(email).get();
		if (!user.isConfirmed()) {
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
		userEntity.setUserType("ROLE_USER");
		BeanUtils.copyProperties(userRepo.save(userEntity), user);
		return user;
	}

	@Override
	public void deleteUser(int id) throws IllegalArgumentException {
		userRepo.deleteById(id);
	}

	@Override
	public boolean updatePassword(PasswordChangeRequest request) {
		UserEntity user = userRepo.findByUsername(request.getUsername()).get();
		if(user.getPassword().equals(request.getOldPassword()) && request.getNewPassword().equals(request.getReNewPassword())) {
			user.setPassword(request.getNewPassword());
			userRepo.save(user);
			return true;
		} 
		else
			return false;
	}	
}
