package com.devcanvas.backend.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devcanvas.backend.entity.User;
import com.devcanvas.backend.exception.UserNameNotFoundException;
import com.devcanvas.backend.repo.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		User user = userRepository.findByUsername(username)
				.orElseThrow(()-> new UserNameNotFoundException("User not found:"+username));
		
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRole())
				.build();
	}
}
