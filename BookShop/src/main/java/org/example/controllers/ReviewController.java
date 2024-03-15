package org.example.controllers;


import org.example.main.Review;
import org.example.repositories.ReviewRepository;

import java.util.List;

public class ReviewController {
    private ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void createReview(int reviewId, int starsNumber, String feedback, int bookId, String date) {
        Review review = new Review(reviewId, starsNumber, feedback, bookId, date);
        reviewRepository.save(review);
    }

    public Review findReviewById(int reviewId) {
        return reviewRepository.findById(reviewId);
    }

    public List<Review> viewAllReviews() {
        return reviewRepository.findAll();
    }

    public void updateReview(int reviewId, int starsNumber, String feedback, int bookId, String date) {
        Review updatedReview = new Review(reviewId, starsNumber, feedback, bookId, date);
        reviewRepository.update(updatedReview);
    }

    public void deleteReview(int reviewId) {
        reviewRepository.delete(reviewId);
    }
}
