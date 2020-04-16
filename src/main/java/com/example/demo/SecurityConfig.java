package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.UDSImplements;

/**
 * Spring Security設定用クラスです.
 * 
 * @author masashi.nose
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private UDSImplements memberDetailsService; //udsの中にはLoginUserの情報が入っている.

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/img/**", "/js/**", "/fonts/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").permitAll()// ログインしていなくてもすべてのパスを認可
				.antMatchers("/user/**").hasRole("USER").anyRequest().authenticated();

		http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login").failureUrl("/toLogin/?error=true")
				.defaultSuccessUrl("/", false).usernameParameter("email").passwordParameter("password");

		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("logout")).logoutSuccessUrl("/")
				.deleteCookies("JSESSIONID").invalidateHttpSession(true);

	}

	/**
	 * ①登録されたユーザーと、 ②ログインで入力された情報があっているのかチェックします.
	 *（ログイン画面で入力した情報と、DBにある情報がマッチしているかここでチェック）
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberDetailsService).passwordEncoder(new BCryptPasswordEncoder());

	}

	/**
	 * パスワードのハッシュ化を行います. ①ユーザー情報登録時、ログイン成功後セッションに格納するときに使います.
	 * 
	 * @return BCryptアルゴリズムでのエンコーダーのインスタンス
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
