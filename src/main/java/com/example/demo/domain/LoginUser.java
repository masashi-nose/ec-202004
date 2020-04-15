package com.example.demo.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * ログイン情報を保持するクラス.
 * 
 * @author masashi.nose
 *
 */
//spring securityの中に元から備わっているUserクラスを継承.
public class LoginUser extends org.springframework.security.core.userdetails.User {

	/** 自分たちが作ったUserクラスと差別化できるように「Version」番号を持たせて区別. */
	private static final long serialVersionUID = 1L;

	private final User user;

	public LoginUser(User user, Collection<GrantedAuthority> authorityList) {
		super(user.getEmail(), user.getPassword(), authorityList);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}
