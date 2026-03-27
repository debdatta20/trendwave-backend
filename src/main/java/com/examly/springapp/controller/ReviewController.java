
package com.examly.springapp.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Review;
import com.examly.springapp.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")

public class ReviewController {
    
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody Review review) {
        Review rr=reviewService.addReview(review);
        if(rr!=null)
        {
        return ResponseEntity.status(201).body("Review added successfully!");
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
       Optional<Review>review=reviewService.getReviewById(reviewId);
       if(review.isPresent()) {
    	   return ResponseEntity.status(200).body(review.get());
       }
       else {
    	   return ResponseEntity.status(404).build();
       }
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> list=reviewService.getAllReviews();
        if(list.isEmpty())
        {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable Long userId) {
        List<Review> reviews = reviewService.getReviewsByUserId(userId);
      
        return ResponseEntity.status(200).body(reviews);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable Long productId) {
        List<Review> reviews = reviewService.getReviewsByProductId(productId);
       
        if(reviews.isEmpty()) {
        	return ResponseEntity.status(404).build();
        }
        else {
        	return ResponseEntity.status(200).body(reviews);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        boolean deleted = reviewService.deleteReview(reviewId);
       
        if(deleted) {
        	return ResponseEntity.status(200).build();
        }
        else {
        	return ResponseEntity.status(404).build();
        }
    }
    
}

