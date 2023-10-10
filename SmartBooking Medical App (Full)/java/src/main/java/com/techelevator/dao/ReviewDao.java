package com.techelevator.dao;

import com.techelevator.model.Review;
import com.techelevator.model.ReviewDto;
import com.techelevator.model.ReviewResponse;
import com.techelevator.model.User;

import java.security.Principal;
import java.util.List;

public interface ReviewDao {

    List<Review> getAllReviews();
    Review getReviewById(int id);
    List<ReviewDto> getReviewsByReviewedOffice(int officeId);
    Review createReview(Review review, Principal principal);
    Review updateReview(Review review);
    ReviewResponse getReviewResponseById (int id);
    ReviewResponse reviewResponse(ReviewResponse reviewResponse);
}
