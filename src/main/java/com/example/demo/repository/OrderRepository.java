package com.example.demo.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Order;

/**
 * ordersテーブルを操作するリポジトリ.
 * 
 * @author masashi.nose
 *
 */
@Repository
public class OrderRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private SimpleJdbcInsert insert;

	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("orders");
		insert = withTableName.usingGeneratedKeyColumns("id");

	}

	/**
	 * ordersテーブルにID自動採番型インサート
	 * 
	 * @param order 注文情報
	 * @return ID自動採番された注文情報
	 */
	@SuppressWarnings("null")
	public Order insert(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);

		if (order == null) {
			Number key = insert.executeAndReturnKey(param);
			order.setId(key.intValue());
		}
		return order;

	}
}
