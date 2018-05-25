package com.tidder.service;

import com.tidder.model.UserEntity;

public interface LoginService {

	public void createAccount(UserEntity user) throws Exception;
	
	public UserEntity getAuthenticatedUser();
}
