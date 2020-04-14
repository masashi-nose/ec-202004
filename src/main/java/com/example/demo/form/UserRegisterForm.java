package com.example.demo.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * ユーザー登録画面からリクエストパラメータを受け取るフォーム.
 * 
 * @author masashi.nose
 *
 */
public class UserRegisterForm {
	/** 名前 */
	@NotBlank(message="名前を入力してください。")
	private String name;
	/** メールアドレス */
	@Email(message="不正なメールアドレスです。")
	private String email;
	/** 郵便番号 */
	@NotBlank(message="郵便番号を入力してください。")
	private String zipcode;
	/** 住所 */
	@NotBlank(message="住所を入力してください。")
	private String address;
	/** 電話番号 */
	@Pattern(regexp="^[0-9]{3}-[0-9]{4}-[0-9]{4}$")
	private String telephone;
	/** パスワード */
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]){8,16}$", message="半角大小英字・数字を用い、8~16字で入力してください。")
	private String password;
	/** 確認用パスワード */
	@NotBlank(message="確認用パスワードを入力してください。")
	private String confirmationPassword;

	@Override
	public String toString() {
		return "UserRegisterForm [name=" + name + ", email=" + email + ", zipcode=" + zipcode + ", address=" + address
				+ ", telephone=" + telephone + ", password=" + password + ", confirmationPassword="
				+ confirmationPassword + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}

}
