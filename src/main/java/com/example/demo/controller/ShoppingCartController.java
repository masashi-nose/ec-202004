package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ShoppingCartService;

/**
 * 
 * ショッピングカートまわりの操作をするコントローラ.
 * 
 * @author masashi.nose
 *
 */
@Controller
@RequestMapping("")
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	

}
