package com.example.demo.domain;

/**
 * 
 * toppingsテーブルのエンティティ
 * 
 * @author masashi.nose
 *
 */
public class Topping {
	/** ID */
	private Integer id;
	/** トッピング名 */
	private String name;
	/** 価格M */
	private Integer priceM;
	/** 価格L */
	private Integer priceL;

	@Override
	public String toString() {
		return "Topping [id=" + id + ", name=" + name + ", priceM=" + priceM + ", priceL=" + priceL + "]";
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

	public Integer getPriceM() {
		return priceM;
	}

	public void setPriceM(Integer priceM) {
		this.priceM = priceM;
	}

	public Integer getPriceL() {
		return priceL;
	}

	public void setPriceL(Integer priceL) {
		this.priceL = priceL;
	}

}
