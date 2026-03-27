
package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.Review;
import com.examly.springapp.repository.ReviewRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    
	private final ReviewRepo reviewRepo;
	
	@Override
	public Review addReview(Review review) {
		review.setDate(LocalDate.now());
		log.info("Adding review for  productId: {}, userId:{}",review.getProduct(),review.getUser());
		return reviewRepo.save(review);
		
	}

	@Override
	public Optional<Review> getReviewById(Long reviewId) {
		log.info("Fetching review with ID: {}",reviewId);
		Optional<Review> rev=reviewRepo.findById(reviewId);
		if(rev.isPresent()){
			log.debug("Review found: {}",rev.get()); //debug – Used to log detailed information useful during development and debugging.
            return rev;
        }
			log.warn("Review with ID {} not found");
            return null;
	}

	@Override
	public List<Review> getAllReviews() {
		log.info("Getting all Reviews");
		return reviewRepo.findAll();
	}

	@Override
	public List<Review> getReviewsByUserId(Long userId) {
		log.info("Fetching reviews by UserId");
		return reviewRepo.findByUserUserId(userId);
	}

	@Override
	public List<Review> getReviewsByProductId(Long productId) {
		log.info("Fetching reviews by ProductId");
		return reviewRepo.findByProductProductId(productId);
	}

	@Override
	public boolean deleteReview(Long reviewId) {
		log.info("Attempting to delete review with ID:{}",reviewId);
		if(reviewRepo.existsById(reviewId)) {
			reviewRepo.deleteById(reviewId);
			log.info("Review with ID {} deleted successfully",reviewId);
			return true;
		}
		log.warn("Review with ID {} does not exist",reviewId);
		return false;
	}
    
}
