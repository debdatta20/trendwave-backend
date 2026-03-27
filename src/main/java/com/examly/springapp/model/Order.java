
package com.examly.springapp.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name="`order`")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private long orderId;
    @Column(name="order_date")
	private LocalDate orderDate;
	@Column(name="order_status")
	private String orderStatus;
	@Column(name="shipping_address")
    private String shippingAddress;
	@Column(name="billing_address")
	private String billingAddress;
	@Column(name="total_amount")
	private double totalAmount;
	
	@ManyToOne
	@JoinColumn(name="userId")
	//@JsonBackReference
	private User user;
	
	@OneToMany(mappedBy="order", cascade=CascadeType.ALL)
	@JsonManagedReference
	List<OrderItem> orderItems;
}

