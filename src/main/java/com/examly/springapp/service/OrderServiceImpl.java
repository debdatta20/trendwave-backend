// package com.examly.springapp.service;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.stereotype.Service;

// import com.examly.springapp.exceptions.DuplicateOrderException;
// import com.examly.springapp.model.Order;
// import com.examly.springapp.repository.OrderRepo;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @Service
// @RequiredArgsConstructor
// public class OrderServiceImpl implements OrderService{

//     private final OrderRepo oRepo;


//     @Override
//     public Order addOrder(Order order) throws DuplicateOrderException{
//         log.info("We are in addOrder OrderServiceImpl");
//         if (oRepo.existsById(order.getOrderId())) {
//             throw new DuplicateOrderException("Order already placed!");
//         }
//         return oRepo.save(order);
//     }

//     @Override
//     public Optional<Order> getOrderById(long orderId) {
//         log.info("We are in getOrderById OrderServiceImpl");
//         return oRepo.findById(orderId);
//     }

//     @Override
//     public List<Order> getAllOrders() {
//         log.info("We are in getAllOrders OrderServiceImpl");
//        return oRepo.findAll();
//     }

//     @Override
//     public Order updateOrder(long orderId, Order updatedOrder) {
//         log.info("We are in updateOrder OrderServiceImpl");
//         Optional<Order> opt=oRepo.findById(orderId);
// 	    Order old=null;
// 	    if(opt.isPresent()) {
// 	    	old=opt.get();
// 	    	old.setOrderDate(updatedOrder.getOrderDate());
// 	    	old.setOrderStatus(updatedOrder.getOrderStatus());
// 	    	old.setBillingAddress(updatedOrder.getBillingAddress());
// 	    	old.setTotalAmount(updatedOrder.getTotalAmount());
// 	    	old.setUser(updatedOrder.getUser());
//             old.setOrderItems(updatedOrder.getOrderItems());
// 	    	oRepo.save(old);
// 	    }
// 	    return old;
//     }

//     @Override
//     public List<Order> getOrdersByUserId(long userId) {
//         log.info("We are in getOrdersByUserId OrderServiceImpl");
//         return oRepo.getOrderById(userId);
//     }

//     @Override
//     public boolean deleteOrder(long orderId) {
//         log.info("We are in deleteOrder OrderServiceImpl");
//         Optional<Order> opt=oRepo.findById(orderId);
//     	if(opt.isPresent()) {
//     		oRepo.deleteById(orderId);
//     		return true;
//     	}
//     	return false;
//     }
    
// }

package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.examly.springapp.exceptions.DuplicateOrderException;
import com.examly.springapp.model.Order;
import com.examly.springapp.model.OrderItem;
import com.examly.springapp.model.Product;
import com.examly.springapp.repository.OrderRepo;
import com.examly.springapp.repository.ProductRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo oRepo;
    private final ProductRepo productRepo;

    @Override
    public Order addOrder(Order order) throws DuplicateOrderException {
        log.info("We are in addOrder OrderServiceImpl");

        if (oRepo.existsById(order.getOrderId())) {
            throw new DuplicateOrderException("Order already placed!");
        }

        // Check and update stock for each product in the order
        for (OrderItem item : order.getOrderItems()) {
            Product product = productRepo.findById(item.getProduct().getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStockInteger() < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getProductName());
            }

            product.setStockInteger(product.getStockInteger() - item.getQuantity());
            productRepo.save(product);
        }

        return oRepo.save(order);
    }

    @Override
    public Optional<Order> getOrderById(long orderId) {
        log.info("We are in getOrderById OrderServiceImpl");
        return oRepo.findById(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        log.info("We are in getAllOrders OrderServiceImpl");
        return oRepo.findAll();
    }

    @Override
    public Order updateOrder(long orderId, Order updatedOrder) {
        log.info("We are in updateOrder OrderServiceImpl");
        Optional<Order> opt = oRepo.findById(orderId);
        if (opt.isPresent()) {
            Order old = opt.get();
            old.setOrderDate(updatedOrder.getOrderDate());
            old.setOrderStatus(updatedOrder.getOrderStatus());
            old.setBillingAddress(updatedOrder.getBillingAddress());
            old.setTotalAmount(updatedOrder.getTotalAmount());
            old.setUser(updatedOrder.getUser());
            old.setOrderItems(updatedOrder.getOrderItems());
            return oRepo.save(old);
        }
        return null;
    }

    @Override
    public List<Order> getOrdersByUserId(long userId) {
        log.info("We are in getOrdersByUserId OrderServiceImpl");
        return oRepo.getOrderById(userId);
    }

    @Override
    public boolean deleteOrder(long orderId) {
        log.info("We are in deleteOrder OrderServiceImpl");
        Optional<Order> opt = oRepo.findById(orderId);
        if (opt.isPresent()) {
            oRepo.deleteById(orderId);
            return true;
        }
        return false;
    }
}
