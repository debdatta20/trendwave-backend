
package com.examly.springapp.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Review;


@Repository

public interface ReviewRepo extends JpaRepository<Review, Long>{
		
	//Find reviews by user ID
	List<Review> findByUserUserId(Long userId);
	
	//Find reviews by product ID
	List<Review> findByProductProductId(Long productId);
}
