package com.example.demo.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 
 * ログイン画面からリクエストパラメータを受け取るフォーム.
 * 
 * @author masashi.nose
 *
 */
public class LoginForm {
	
	/** メールアドレス */
	@Email(message = "メールアドレスの形式ではありません" )
	@NotBlank(message = "メールアドレスを入力してください")
	private String email;

	/** パスワード */
	@NotBlank(message = "パスワードを入力してください")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginUserForm [email=" + email + ", password=" + password + "]";
	}

}
