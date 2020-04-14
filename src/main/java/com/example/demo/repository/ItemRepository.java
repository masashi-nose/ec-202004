package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Item;

/**
 * itemsテーブルを操作するリポジトリです.
 * 
 * @author masashi.nose
 *
 */
@Repository
public class ItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * itemsテーブル１行分のデータを保持するローマッパー
	 * 
	 */
	private final static RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
		item.setDescription(rs.getString("description"));
		item.setDeleted(rs.getBoolean("deleted"));
		return item;
	};

	/**
	 * itemsテーブルから商品情報を全件検索します.
	 * 
	 * @return 全商品情報.
	 */
	public List<Item> findAll() {
		String sql = "SELECT id, name, price_m, price_l, image_path, description, deleted FROM items ORDER BY price_m";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}

	/**
	 * idを用いて商品を１件検索します.
	 * 
	 * @param id 商品ID
	 * @return　商品情報
	 */
	public Item load(Integer id) {
		String sql = "SELECT id, name, price_m, price_l, image_path, description, deleted FROM items WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		return item;

	}

}
