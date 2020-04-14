package com.example.demo.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

/**
 * usersテーブルを操作するリポジトリです.
 * 
 * @author masashi.nose
 *
 */
@Repository
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * usersテーブル１行分のデータを保持するローマッパー.
	 */
	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setZipcode(rs.getString("zipcode"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));
		user.setPassword(rs.getString("password"));
		return user;
	};

	/** IDが自動採番されたオブジェクトが返ってくるインサートの準備 */
	private SimpleJdbcInsert insert;

	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("users");
		insert = withTableName.usingGeneratedKeyColumns("id");

	}

	/**
	 * usersテーブルにユーザー情報を挿入します.
	 * 
	 * @param user ユーザー情報
	 * @return IDが自動採番されたUserオブジェクト
	 */
	public User insertUser(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);

		if (user.getId() == null) {
			Number key = insert.executeAndReturnKey(param);
			user.setId(key.intValue());

		}

		return user;
	}
}
