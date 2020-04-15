package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * ログインに関するコントローラ.
 * 
 * @author masashi.nose
 *
 */
@Controller
@RequestMapping("")
public class LoginController {

	/**
	 * ログイン画面へ遷移します.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}
}
