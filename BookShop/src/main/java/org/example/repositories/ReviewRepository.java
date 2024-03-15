package org.example.repositories;

import org.example.main.Patterns.Singelton.ReviewRepositorySingelton;
import org.example.main.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewRepository implements ReviewRepositorySingelton{
    private static ReviewRepository instance;
    private List<Review> reviews = new ArrayList<>();

    private ReviewRepository(List<Review> reviews) {
        this.reviews = reviews;
    }


    public static ReviewRepository getInstance(List<Review> reviews) {
        if (instance == null) {
            instance = new ReviewRepository(reviews);
        }
        return instance;
    }
    @Override
    public void reset() {
        reviews.clear();
    }

    public Review findById(int targetReviewId) {
        for (Review review : reviews) {
            if (review.getReview_id() == targetReviewId) {
                return review;
            }
        }
        System.out.println("Could not find review with Id:" + targetReviewId);
        return null;
    }

    public List<Review> findAll() {
        if (reviews.isEmpty()) {
            System.out.println("There are no reviews");
            return null;
        }
        return reviews;
    }

    public boolean save(Review review) {
        boolean saved = false;
        for (Review item : reviews) {
            if (review.getReview_id() == item.getReview_id()) {
                System.out.println("Order already exists");
                return saved;
            }

        }
        reviews.add(review);
        for (Review item : reviews) {
            if (review.getReview_id() == item.getReview_id())
                saved = true;

        }

        return saved;
    }

    public boolean update(Review updatedReview) {
        boolean updated = false;
        for (Review review : reviews) {
            if (review.getReview_id() == updatedReview.getReview_id()) {
                review.setStars_number(updatedReview.getStars_number());
                review.setFeedback(updatedReview.getFeedback());
                review.setBook_id(updatedReview.getBook_id());
                review.setDate(updatedReview.getDate());
                updated = true;
                break;
            }
        }
        if (updated == false)
            System.out.println("Order was not updated");

        return updated;
    }

    public boolean delete(int targetReviewId) {
        boolean deleted = false;
        Review reviewToRemove = null;
        for (Review review : reviews) {
            if (review.getReview_id() == targetReviewId) {
                reviewToRemove = review;
                break;
            }
        }
        if (reviewToRemove != null) {
            reviews.remove(reviewToRemove);
            deleted=true;
        }


        if (reviewToRemove == null) {
            System.out.println("Review does not exist");
            deleted = false;
        }
        return deleted;

    }

}
