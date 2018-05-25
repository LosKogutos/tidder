package com.tidder.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tidder.api.dto.CustomUserPrincipal;
import com.tidder.model.AuthoritiesEntity;
import com.tidder.model.UserEntity;
import com.tidder.repository.LoginRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = loginRepository.getUserWithAuthByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("Email: " + email + " not found.");
		}
		Set<AuthoritiesEntity> roles = user.getAuthorities();

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(AuthoritiesEntity role: roles){
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        return new CustomUserPrincipal(user,authorities);
	}

}
