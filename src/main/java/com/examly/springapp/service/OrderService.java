
package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import com.examly.springapp.exceptions.DuplicateOrderException;
import com.examly.springapp.model.Order;


public interface OrderService {
	
	Order addOrder(Order order)throws DuplicateOrderException;
	Optional<Order> getOrderById(long orderId);
	List<Order> getAllOrders();
	Order updateOrder(long orderId, Order updatedOrder);
	List<Order> getOrdersByUserId(long userId);
    boolean deleteOrder(long orderId);

}

