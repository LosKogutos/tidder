package com.tidder.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tidder.model.AuthoritiesEntity;
import com.tidder.model.UserEntity;
import com.tidder.repository.LoginRepository;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	private static final String role = "ROLE_USER";
	
	@Autowired
	private LoginRepository loginRepository;

	@Override
	public void createAccount(UserEntity user) throws Exception {
		
		//check if email is already taken
		UserEntity existingUser = loginRepository.findByEmail(user.getEmail());
		if(existingUser!=null) {
			throw new Exception("You already created account with this email!");
		}
		
		//encode the password
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
		user.setPassword(encoder.encode(user.getPassword()));
		
		//bind with authority
		AuthoritiesEntity auth = new AuthoritiesEntity();
		auth.setAuthority(role);
		auth.setUser(user);
		Set<AuthoritiesEntity> authList = new HashSet<AuthoritiesEntity>();
		authList.add(auth);
		user.setAuthorities(authList);
		user.setEnabled(true);
		
		//save
		try {
			loginRepository.save(user);
		} catch (Exception e) {
			throw new Exception("Unable to save user");
		}
		
	}
	
	@Transactional
	public UserEntity getAuthenticatedUser() {		
		return loginRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());		
	}

}
