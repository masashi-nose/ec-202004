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

import com.example.demo.domain.OrderTopping;

/**
 * 
 * ordertoppingsテーブルを操作するリポジトリ.
 * 
 * @author masashi.nose
 *
 */
@Repository
public class OrderToppingRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * order_toppingsテーブル１行分のデータを保持するローマッパー.
	 * 
	 */
	private static final RowMapper<OrderTopping> ORDER_TOPPING_ROWMAPPER = (rs, i) -> {
		OrderTopping orderTopping = new OrderTopping();
		orderTopping.setId(rs.getInt("id"));
		orderTopping.setOrderItemId(rs.getInt("order_item_id"));
		orderTopping.setToppingId(rs.getInt("topping_id"));
		return orderTopping;
	};

	/** 自動採番IDオブジェクトをリターンするインサートメソッドの準備. */
	private SimpleJdbcInsert insert;

	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("order_toppings");
		insert = withTableName.usingGeneratedKeyColumns("id");

	}

	/**
	 * ID自動採番型インサート.
	 * 
	 * @param orderTopping
	 * @return IDが自動採番されたorderToppingオブジェクト
	 */
	public OrderTopping insert(OrderTopping orderTopping) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderTopping);

		if (orderTopping.getId() == null) {
			Number key = insert.executeAndReturnKey(param);
			orderTopping.setId(key.intValue());
		}

		return orderTopping;
	}

}
