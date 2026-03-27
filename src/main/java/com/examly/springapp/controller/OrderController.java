
package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.exceptions.DuplicateOrderException;
import com.examly.springapp.model.Order;
import com.examly.springapp.service.OrderServiceImpl;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
public class OrderController {
	

	private final OrderServiceImpl oService;
	
	@PostMapping("/api/orders")
	public ResponseEntity<String> addOrder(@RequestBody Order order)throws DuplicateOrderException{
		Order od=oService.addOrder(order);
		if(od!=null) {
			String s="Order Placed Successfully!!";
			return new ResponseEntity<>(s, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

    @GetMapping("api/orders/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable long orderId){
        Optional<Order> opt=oService.getOrderById(orderId);
        if(opt.isPresent()){
            return new ResponseEntity<>(opt.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@GetMapping("/api/orders/user/{userId}")
	public ResponseEntity<List<Order>> getOrderByUserId(@PathVariable long userId){
		List<Order> olist=oService.getOrdersByUserId(userId);
		if(!olist.isEmpty()) {
			return new ResponseEntity<>(olist, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/api/orders")
	public ResponseEntity<List<Order>> getAllOrders(){
		List<Order> od=oService.getAllOrders();
		if(!od.isEmpty()) {
			return new ResponseEntity<>(od, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/api/orders/{orderId}")
	public ResponseEntity<Order> updateOrder(@PathVariable long orderId, @RequestBody Order updatedOrder){
		Order od=oService.updateOrder(orderId, updatedOrder);
		if(od!=null) {
			return new ResponseEntity<>(od, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/api/orders/{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable long orderId){
		boolean od=oService.deleteOrder(orderId);
		if(od) {
			return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Failed to delete order", HttpStatus.NOT_FOUND);
		}
	}

}
