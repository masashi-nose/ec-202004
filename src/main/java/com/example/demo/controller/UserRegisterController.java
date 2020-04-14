package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.User;
import com.example.demo.form.UserRegisterForm;
import com.example.demo.service.UserRegisterService;

/**
 * usersテーブルを操作するコントローラです.
 * 
 * @author masashi.nose
 *
 */
@Controller
@RequestMapping("")
public class UserRegisterController {

	@Autowired
	private UserRegisterService userRegisterService;

	@ModelAttribute
	public UserRegisterForm setUpForm() {
		return new UserRegisterForm();
	}

	/**
	 * ユーザー登録画面に遷移します.
	 * 
	 * @return ユーザー登録画面
	 */
	@RequestMapping("/toRegister")
	public String toRegister() {
		return "register";
	}

	/**
	 * ユーザー登録を行います.登録後、ログイン画面に遷移します.
	 * 
	 * @param form リクエストパラメータ
	 * @param result　
	 * @param model　リクエストスコープ
	 * @return　ログイン画面
	 */
	@RequestMapping("/register")
	public String register(@Validated UserRegisterForm form, BindingResult result, Model model) {
		User user = new User();

		user.setName(form.getName());
		user.setEmail(form.getEmail());
		user.setZipcode(form.getZipcode());
		user.setAddress(form.getAddress());
		user.setPassword(form.getPassword());

		userRegisterService.register(user);

		return "redirect: /toLogin";
	}

}
