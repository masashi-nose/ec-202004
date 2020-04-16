package com.example.demo.domain;

import java.util.List;

/**
 * orderitemsテーブルのエンティティ.
 * 
 * @author masashi.nose
 *
 */
public class OrderItem {
	/** ID */
	private Integer id;
	/** 注文商品ID */
	private Integer orderItemId;
	/** 注文ID */
	private Integer orderId;
	/** 数量 */
	private Integer quantity;
	/** 商品サイズ */
	private Character size;
	/** 商品インスタンス */
	private Item item;
	/** 注文トッピングリスト */
	private List<OrderTopping> orderToppingList;

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", orderItemId=" + orderItemId + ", orderId=" + orderId + ", quantity="
				+ quantity + ", size=" + size + ", item=" + item + ", orderToppingList=" + orderToppingList + "]";
	}

	/**
	 * 小計金額を計算するゲッター
	 * 
	 * @return 注文商品の小計金額
	 */
	public int getSubTotal() {
		int subTotal = 0;

		if (size == 'M') {
			int toppingPrice = 200 * orderToppingList.size();
			int itemPrice = item.getPriceM();
			subTotal = (itemPrice + toppingPrice) * quantity;

		} else {
			int toppingPrice = 300 * orderToppingList.size();
			int itemPrice = item.getPriceM();
			subTotal = (itemPrice + toppingPrice) * quantity;
		}

		return subTotal;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Character getSize() {
		return size;
	}

	public void setSize(Character size) {
		this.size = size;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

}
