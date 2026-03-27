
package com.examly.springapp.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="review_id")
	private Long reviewId;
	@Column(name="review_text")
	private String reviewText;
	@Column(name="rating")
	private Integer rating;
	@Column(name="date")
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name="user_Id")
	//@JsonBackReference
	private User user;

	@ManyToOne
	@JoinColumn(name="product_id")
	// @JsonBackReference
	// @JsonIgnore
	private Product product;    
}

