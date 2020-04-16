package com.example.demo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.OrderTopping;
import com.example.demo.form.ShoppingCartForm;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.OrderToppingRepository;

/**
 * ショッピングカートまわりの業務処理を行うサービスクラス.
 * 
 * @author masashi.nose
 *
 */
public class ShoppingCartService {

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderToppingRepository orderToppingRepository;

	@Autowired
	private OrderRepository orderRepository;

	@ModelAttribute
	public ShoppingCartForm setUpForm() {
		return new ShoppingCartForm();

	}

	/**
	 * ショッピングカートに商品を追加します. orders/orderItems/orderToppingsに追加します
	 * 
	 * @param form   リクエストパラメータ
	 * @param userId ユーザーID
	 */
	public void insert(ShoppingCartForm form, int userId) {
		Order userOrder = orderRepository.findByUserIdAndStatus(userId, 0);

//		ユーザーIDで検索した注文情報がなければ、注文オブジェクトを新規作成
//		orders/orderItems/orderToppingsにインサート	.
		if (userOrder == null) {
			Order order = new Order();
			order.setUserId(userId);
			order.setStatus(0);
			order.setTotalPrice(0);
			order = orderRepository.insert(order);

//			注文商品オブジェクトを生成し、インサート。
			OrderItem orderItem = new OrderItem();
			BeanUtils.copyProperties(form, orderItem);
			orderItem.setOrderId(order.getId());
			orderItem = orderItemRepository.insert(orderItem);

			if (form.getToppingList() != null) {
				for (Integer topping : form.getToppingList()) {
					OrderTopping orderTopping = new OrderTopping();
					orderTopping.setToppingId(topping);
					orderTopping.setOrderItemId(orderItem.getId());
					orderToppingRepository.insert(orderTopping);
				}

			}
//		ユーザーIDと注文状況で検索した注文情報のステータスが0の時の処理.
//		orderItems/orderToppingsにインサート
		} else if (userOrder.getStatus() == 0) {
			OrderItem orderItem = new OrderItem();
			BeanUtils.copyProperties(form, orderItem);
			orderItem.setOrderId(userOrder.getId());
			orderItem = orderItemRepository.insert(orderItem);

			if (form.getToppingList() != null) {
				for (Integer topping : form.getToppingList()) {
					OrderTopping orderTopping = new OrderTopping();
					orderTopping.setToppingId(topping);
					orderTopping.setOrderItemId(orderItem.getId());
					orderToppingRepository.insert(orderTopping);
				}
			}
		}
	}

}
