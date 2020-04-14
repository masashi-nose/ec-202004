package com.example.demo.domain;

import java.util.List;

/**
 * itemsテーブルのエンティティ.
 * 
 * @author masashi.nose
 *
 */
public class Item {
	/** ID */
	private Integer id;
	/** 名前 */
	private String name;
	/** 商品詳細 */
	private String description;
	/** 価格M */
	private Integer price_m;
	/** 価格L */
	private Integer price_l;
	/** 画像パス */
	private String imagePath;
	/** 削除フラグ */
	private boolean deleted;
	/** トッピングリスト */
	private List<Topping> toppingList;

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", price_m=" + price_m
				+ ", price_l=" + price_l + ", imagePath=" + imagePath + ", deleted=" + deleted + ", toppingList="
				+ toppingList + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice_m() {
		return price_m;
	}

	public void setPrice_m(Integer price_m) {
		this.price_m = price_m;
	}

	public Integer getPrice_l() {
		return price_l;
	}

	public void setPrice_l(Integer price_l) {
		this.price_l = price_l;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public List<Topping> getToppingList() {
		return toppingList;
	}

	public void setToppingList(List<Topping> toppingList) {
		this.toppingList = toppingList;
	}

}
