package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.domain.Topping;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.ToppingRepository;

/**
 * 商品詳細表示をする業務処理を行うサービスクラス.
 * 
 * @author masashi.nose
 *
 */
@Service
@Transactional
public class ShowItemDetailService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ToppingRepository toppingRepository;

	/**
	 * 
	 * IDを用いて商品を１件検索します.
	 * 
	 * @param id 商品ID
	 * @return 商品情報
	 */
	public Item showDetail(Integer id) {
		return itemRepository.load(id);

	}

	/**
	 * 
	 * toppingsテーブルからトッピングを全件取得し表示します.
	 * 
	 * @return トッピング全件情報
	 */
	public List<Topping> showToppingList() {
		return toppingRepository.findAll();
	}
}
