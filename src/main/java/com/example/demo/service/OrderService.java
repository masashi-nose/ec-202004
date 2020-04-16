package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Order;
import com.example.demo.repository.OrderRepository;

/**
 * 注文まわりの業務処理を行うサービスクラス.
 * 
 * @author masashi.nose
 *
 */
@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * ユーザーIDと注文状況から注文情報を取得します.
	 * 
	 * @param userId ユーザーID
	 * @param status　注文状況
	 * @return　注文情報
	 */
	public Order findByUserIdAndStatus(int userId, int status) {
		return orderRepository.findByUserIdAndStatus(userId, status);
	}
}
