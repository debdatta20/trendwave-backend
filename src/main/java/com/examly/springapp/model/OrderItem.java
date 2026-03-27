
package com.examly.springapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="order_item")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="order_item_id")
	private long orderItemId;
    @Column(name="quantity")
	private int quantity;
    @Column(name="price")
	private double price;
	
	@ManyToOne
	@JoinColumn(name="productId")
	//@JsonBackReference
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="orderId")
   	@JsonBackReference
	private Order order;

}

