package com.my.employeeproject.service;

import com.my.employeeproject.entity.User;
import com.my.employeeproject.repository.UserRepository;
import com.my.employeeproject.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Sorry! User Not Found");
		}
		return new MyUserDetails(user);
	    }

}



