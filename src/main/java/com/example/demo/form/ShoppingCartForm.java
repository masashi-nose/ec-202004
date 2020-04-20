package com.example.demo.form;

import java.util.List;

/**
 * 商品詳細画面からリクエストパラメータを受け取るフォーム.
 * 
 * @author masashi.nose
 *
 */
public class ShoppingCartForm {

	/** 商品ID */
	private Integer itemId;
	/** 数量 */
	private Integer quantity;
	/** サイズ */
	private Character size;
	/** トッピングリスト */
	private List<Integer> orderToppingList;

	@Override
	public String toString() {
		return "ShoppingCartForm [itemId=" + itemId + ", quantity=" + quantity + ", size=" + size
				+ ", orderToppingList=" + orderToppingList + "]";
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
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

	public List<Integer> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<Integer> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

}
