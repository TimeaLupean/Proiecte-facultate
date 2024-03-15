package tests;

import org.example.main.Review;
import org.example.repositories.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewRepositoryTest {

    @BeforeEach
    void setUp() {
        ReviewRepository.getInstance(new ArrayList<>()).reset();
    }

    @Test
    void findByIdTest_expected() {
        ReviewRepository reviewRepository = ReviewRepository.getInstance(new ArrayList<>());
        Review review = new Review(1, 5, "Great book!", 123, "2023-10-29");
        reviewRepository.save(review);
        Review foundReview = reviewRepository.findById(1);

        assertNotNull(foundReview, "Failed to find the review by Id");
        assertEquals(review, foundReview, "Found a different review");
    }

    @Test
    void findByIdTest_unexpected() {
        ReviewRepository reviewRepository = ReviewRepository.getInstance(new ArrayList<>());
        Review review = new Review(1, 5, "Great book!", 123, "2023-10-29");
        reviewRepository.save(review);
        Review foundReview = reviewRepository.findById(2);

        assertNull(foundReview, "Found a non-existing review");
    }

    @Test
    void findAllTest_expected() {
        ReviewRepository reviewRepository = ReviewRepository.getInstance(new ArrayList<>());
        Review review1 = new Review(1, 5, "Great book!", 123, "2023-10-29");
        Review review2 = new Review(2, 4, "Good read!", 234, "2023-10-30");
        reviewRepository.save(review1);
        reviewRepository.save(review2);
        List<Review> foundReviews = reviewRepository.findAll();

        assertEquals(2, foundReviews.size(), "Failed to find all reviews");
        assertTrue(foundReviews.contains(review1), "Missing review1 in the result");
        assertTrue(foundReviews.contains(review2), "Missing review2 in the result");
    }

    @Test
    void findAllTest_unexpected() {
        ReviewRepository reviewRepository =  ReviewRepository.getInstance(new ArrayList<>());
        List<Review> foundReviews = reviewRepository.findAll();

        assertNull(foundReviews, "Found reviews in an empty repository");
    }

    @Test
    void saveTest_expected() {
        ReviewRepository reviewRepository =  ReviewRepository.getInstance(new ArrayList<>());
        Review review = new Review(1, 5, "Great book!", 123, "2023-10-29");
        boolean saved = reviewRepository.save(review);

        assertTrue(saved, "Failed to save the review");
        List<Review> foundReviews = reviewRepository.findAll();
        assertEquals(1, foundReviews.size(), "Failed to save the review correctly");
        assertTrue(foundReviews.contains(review), "Saved review not found in the repository");
    }

    @Test
    void saveTest_unexpected() {
        ReviewRepository reviewRepository = ReviewRepository.getInstance(new ArrayList<>());
        Review review = new Review(1, 5, "Great book!", 123, "2023-10-29");
        reviewRepository.save(review);
        boolean savedAgain = reviewRepository.save(review);

        assertFalse(savedAgain, "Saved a review with duplicate Id");
        List<Review> foundReviews = reviewRepository.findAll();
        assertEquals(1, foundReviews.size(), "Saved a duplicate review");
    }

    @Test
    void updateTest_expected() {
        ReviewRepository reviewRepository =  ReviewRepository.getInstance(new ArrayList<>());
        Review review = new Review(1, 5, "Great book!", 123, "2023-10-29");
        reviewRepository.save(review);
        Review updatedReview = new Review(1, 4, "Good book!", 123, "2023-10-30");
        boolean updated = reviewRepository.update(updatedReview);

        assertTrue(updated, "Failed to update the review");
        Review foundReview = reviewRepository.findById(1);
        assertNotNull(foundReview, "Review not found after update");
    }

    @Test
    void updateTest_unexpected() {
        ReviewRepository reviewRepository =  ReviewRepository.getInstance(new ArrayList<>());
        Review review = new Review(1, 5, "Great book!", 123, "2023-10-29");
        reviewRepository.save(review);
        Review updatedReview = new Review(2, 4, "Good book!", 123, "2023-10-30");
        boolean updated = reviewRepository.update(updatedReview);

        assertFalse(updated, "Updated a non-existing review");
    }

    @Test
    void deleteTest_expected() {
        ReviewRepository reviewRepository =  ReviewRepository.getInstance(new ArrayList<>());
        Review review = new Review(1, 5, "Great book!", 123, "2023-10-29");
        reviewRepository.save(review);
        boolean deleted = reviewRepository.delete(1);

        assertTrue(deleted, "Failed to delete the review");
        Review foundReview = reviewRepository.findById(1);
        assertNull(foundReview, "Review still exists after deletion");
    }

    @Test
    void deleteTest_unexpected() {
        ReviewRepository reviewRepository =  ReviewRepository.getInstance(new ArrayList<>());
        Review review = new Review(1, 5, "Great book!", 123, "2023-10-29");
        reviewRepository.save(review);
        boolean deleted = reviewRepository.delete(2);

        assertFalse(deleted, "Deleted a non-existing review");
        Review foundReview = reviewRepository.findById(1);
        assertNotNull(foundReview, "Deleted the wrong review");
    }

}
