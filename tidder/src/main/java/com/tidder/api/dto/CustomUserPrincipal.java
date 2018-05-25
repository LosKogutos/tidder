package com.tidder.api.dto;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tidder.model.UserEntity;

public class CustomUserPrincipal implements UserDetails {
	private static final long serialVersionUID = -3953720140097926338L;
	
	private UserEntity user;
	Set<GrantedAuthority> authorities = null;



	public CustomUserPrincipal(UserEntity user, Set<GrantedAuthority> auth) {
		this.authorities=auth;
		this.user=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	
	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return user.isEnabled();
	}

}
