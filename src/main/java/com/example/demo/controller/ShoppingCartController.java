package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.LoginUser;
import com.example.demo.form.ShoppingCartForm;
import com.example.demo.service.OrderService;
import com.example.demo.service.ShoppingCartService;

/**
 * 
 * ショッピングカートまわりの操作をするコントローラ.
 * 
 * @author masashi.nose
 *
 */
@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private HttpSession session;

	@ModelAttribute
	public ShoppingCartForm setUpForm() {
		return new ShoppingCartForm();
	}

	@RequestMapping("/insert")
	public String addShoppingCart(ShoppingCartForm form, @AuthenticationPrincipal LoginUser loginUser) {
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			session.setAttribute("userId", session.getId());

		}

		if (userId != null) {
			userId = loginUser.getUser().getId();

		}

		shoppingCartService.insert(form, userId);
		return "redirect:/";
	}

}
