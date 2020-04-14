package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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

	/**
	 * ユーザー登録画面に遷移します.
	 * 
	 * @return ユーザー登録画面
	 */
	@RequestMapping("toRegister")
	public String toRegister() {
		return "register";
	}

}
