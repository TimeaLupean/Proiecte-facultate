package org.example.main.Patterns.Singelton;

import org.example.main.Review;
import org.example.repositories.ReviewRepository;

import java.util.List;

public interface ReviewRepositorySingelton {
    static ReviewRepository getInstance(List<Review> reviews){
        return ReviewRepository.getInstance(reviews);
    };
    public void reset();
}
