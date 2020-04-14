package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

/**
 * ユーザー登録の業務処理を行うサービスクラス.
 * 
 * @author masashi.nose
 *
 */
@Service
@Transactional
public class UserRegisterService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * ユーザー登録を行います. （ユーザー情報をusersテーブルにインサートします）
	 * 
	 * @param user ユーザー情報
	 * @return IDが自動採番されたUserオブジェクト
	 */
	public User register(User user) {
		return userRepository.insertUser(user);

	}

	/**
	 * メールアドレスからユーザーを検索します.
	 * 
	 * @param email メールアドレス
	 * @return ユーザー情報
	 */
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
