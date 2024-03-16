package com.study.skeletonPj.api.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.skeletonPj.api.user.domain.User;
import com.study.skeletonPj.api.user.mapper.UserMapper;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User.Veo loadUserByUsername(String userId) {
		return userMapper.getItem(userId);
	}
	
	public List<User.Vo> getItemList(User.Veo user) {
		List<User.Veo> item = userMapper.getItemList(user);
		
		return User.Vo.of(item);
	}
	
	public User.Vo getItem(String userId) {
		User.Veo item = userMapper.getItem(userId);
		
		return User.Vo.of(item);
	}
	
	public int createItem(User.Veo user) {
		user.setUserPw(new BCryptPasswordEncoder().encode(user.getUserPw()));
		int cnt = userMapper.createItem(user);
		
		return cnt;
	}
	
	public int deleteItem(String user) {
		int cnt = userMapper.deleteItem(user);
		return cnt;
	}
	
	public int putItem(User.Veo user) {
		int cnt = userMapper.putItem(user);
		return cnt;
	}
	
	public int patchItem(User.Veo user) {
		int cnt = userMapper.patchItem(user);
		return cnt;
	}
	
}
