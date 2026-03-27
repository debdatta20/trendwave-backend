package com.examly.springapp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private long productId;
	@Column(name="product_name")
	private String productName;
	@Column(name="descripion")
	private String descripion;
	@Column(name="price")
	private double price;
	@Column(name="stock_integer")
	private int stockInteger;
	@Column(name="category")
	private String category;
	@Column(name="brand")
	private String brand;
	@Lob
	@Column(columnDefinition ="LONGBLOB" ,name = "cover_image")
	private String  coverImage;

	@OneToMany(mappedBy="product", cascade=CascadeType.ALL)
	// @JsonIgnore
	@JsonIgnoreProperties("product")
	private List<Review> reviews;
	
}


