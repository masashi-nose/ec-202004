package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Item;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.OrderTopping;
import com.example.demo.domain.Topping;

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

	private static final ResultSetExtractor<List<Order>> ORDER_RESULT_SET_EXTRACTOR = (rs) -> {
		Order order = new Order();
		List<OrderTopping> orderToppingList = new ArrayList<>();
		List<OrderItem> orderItemList = new ArrayList<>();
		List<Topping> toppingList = new ArrayList<>();
		List<Order> orderList = new ArrayList<>();

		int checkOrderId = 0;
		int firstOrderItemId = 0;

		while (rs.next()) {
			int currentOrderId = rs.getInt("order_id");
			if (currentOrderId != checkOrderId) {
				order = new Order();
				orderItemList = new ArrayList<>();
				order.setId(rs.getInt("order_id"));
				order.setUserId(rs.getInt("order_user_id"));
				order.setStatus(rs.getInt("order_status"));
				order.setTotalPrice(rs.getInt("order_total_price"));
				order.setOrderDate(rs.getDate("order_date"));
				order.setDestinationName(rs.getString("order_destination_name"));
				order.setDestinationEmail(rs.getString("order_destination_email"));
				order.setDestinationZipcode(rs.getString("order_destination_zipcode"));
				order.setDestinationAddress(rs.getString("order_destination_address"));
				order.setDestinationTel(rs.getString("order_destination_tel"));
				order.setDeliveryTime(rs.getTimestamp("order_delivery_time"));
				order.setPaymentMethod(rs.getInt("order_payment_method"));
				order.setOrderItemList(orderItemList);
				orderList.add(order);

			}

			if (rs.getInt("orderitem_id") != firstOrderItemId) {
				OrderItem orderItem = new OrderItem();
				Item item = new Item();
				toppingList = new ArrayList<>();
				orderToppingList = new ArrayList<>();
				orderItemList.add(orderItem);

				orderItem.setId(rs.getInt("orderitem_id"));
				orderItem.setItemId(rs.getInt("orderitem_item_id"));
				orderItem.setOrderId(rs.getInt("orderitem_order_id"));
				orderItem.setQuantity(rs.getInt("orderitem_quantity"));
				orderItem.setSize(rs.getString("orderitem_size").toCharArray()[0]);
				orderItem.setItem(item);
				orderItem.setOrderToppingList(orderToppingList);

				item.setId(rs.getInt("item_id"));
				item.setName(rs.getString("item_name"));
				item.setDescription(rs.getString("item_description"));
				item.setPriceM(rs.getInt("item_price_m"));
				item.setPriceL(rs.getInt("item_price_l"));
				item.setImagePath(rs.getString("item_image_path"));
				item.setDeleted(rs.getBoolean("item_deleted"));
				item.setToppingList(toppingList);

			}

			if (rs.getInt("ordertopping_id") != 0) {
				OrderTopping orderTopping = new OrderTopping();
				Topping topping = new Topping();
				toppingList.add(topping);
				orderToppingList.add(orderTopping);

				orderTopping.setId(rs.getInt("ordertopping_id"));
				orderTopping.setToppingId(rs.getInt("ordertopping_topping_id"));
				orderTopping.setOrderItemId(rs.getInt("ordertopping_order_item_id"));
				orderTopping.setTopping(topping);

				topping.setId(rs.getInt("topping_id"));
				topping.setName(rs.getString("topping_name"));
				topping.setPriceM(rs.getInt("topping_price_m"));
				topping.setPriceL(rs.getInt("topping_price_l"));

			}

			firstOrderItemId = rs.getInt("orderitem_id");
			checkOrderId = rs.getInt("order_id");

		}
		return orderList;

	};

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
	public Order insert(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);

		if (order.getId() == null) {
			Number key = insert.executeAndReturnKey(param);
			order.setId(key.intValue());
		}
		return order;

	}

	/**
	 * ユーザーIDと注文状況から注文情報を検索します.
	 * 
	 * @param userId ユーザーID
	 * @param status　注文状況
	 * @return　注文情報
	 */
	public Order findByUserIdAndStatus(Integer userId, Integer status) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT o.id order_id, o.user_id order_user_id, o.status order_status, o.total_price order_total_price, o.order_date, o.destination_name order_destination_name, o.destination_email order_destination_email, o.destination_zipcode order_destination_zipcode, o.destination_address order_destination_address, o.destination_tel order_destination_tel, o.delivery_time order_delivery_time, o.payment_method order_payment_method, ");
		sql.append(
				"oi.id orderitem_id, oi.item_id orderitem_item_id, oi.order_id orderitem_order_id, oi.quantity orderitem_quantity, oi.size orderitem_size, ");
		sql.append(
				"ot.id ordertopping_id, ot.topping_id ordertopping_topping_id, ot.order_item_id ordertopping_order_item_id, ");
		sql.append(
				"i.id item_id, i.name item_name, i.description item_description, i.price_m item_price_m, i.price_l item_price_l, i.image_path item_image_path, i.deleted item_deleted, ");
		sql.append("t.id topping_id, t.name topping_name, t.price_m topping_price_m, t.price_l topping_price_l ");
		sql.append(
				"FROM orders o join order_items oi on o.id = oi.order_id left join order_toppings ot on oi.id = ot.order_item_id inner join items i on oi.item_id = i.id inner join toppings t on ot.topping_id = t.id ");
		sql.append("WHERE o.user_id = :userId AND o.status = :status ORDER by o.id");

		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);

		List<Order> orderList = template.query(sql.toString(), param, ORDER_RESULT_SET_EXTRACTOR);

		if (orderList.size() == 0) {
			return null;
		}

		return orderList.get(0);

	}

}
