package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.LoginUser;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@Service
public class UDSImplements implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		User user = userRepository.findByEmail(email);
	
	Collection<GrantedAuthority> authorityList = new ArrayList<>();
	authorityList.add(new SimpleGrantedAuthority("ROLE_USER")); //権限付与
	
//	if(member.isAdmin) {
//		authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN")); 管理者権限付与
//	}
	
	//DBからとってきたuserと、ログイン画面から来た情報がトークンとなって、authorityListに入る.
	return new LoginUser(user, authorityList);
		
		
	}
	
}
