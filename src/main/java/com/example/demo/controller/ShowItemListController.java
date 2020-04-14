package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Item;
import com.example.demo.service.ShowItemListService;

/**
 * itemsテーブルを操作するコントローラ.
 * 
 * @author masashi.nose
 *
 */
@Controller
@RequestMapping("")
public class ShowItemListController {

	@Autowired
	private ShowItemListService showItemListService;

	/**
	 * 
	 * 商品一覧を表示します.
	 * 
	 * @param model リクエストスコープ作成
	 * @return 商品一覧画面
	 */
	@RequestMapping("")
	public String showList(Model model) {
		List<Item> itemList = showItemListService.showItemList();

		model.addAttribute("itemList", itemList);
		return "item_list";
	}
}
