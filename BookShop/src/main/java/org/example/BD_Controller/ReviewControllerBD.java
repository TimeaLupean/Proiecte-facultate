package org.example.BD_Controller;


import org.example.BD_Repository.ReviewRepositoryBD;
import org.example.main.Review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReviewControllerBD {
    private ReviewRepositoryBD reviewRepositoryBD;

    public ReviewControllerBD(ReviewRepositoryBD ReviewRepositoryBD) {
        this.reviewRepositoryBD = ReviewRepositoryBD;
    }

    public void saveIntoDB(Review review){
        reviewRepositoryBD.saveIntoDB(review);
    }

    public Review createReviewFromResultSet(ResultSet resultSet) throws SQLException{
        return reviewRepositoryBD.createReviewFromResultSet(resultSet);
    }

    public List<Review> loadFromDB(){
        return reviewRepositoryBD.loadFromDB();
    }

    public Review findById(int id){
        return reviewRepositoryBD.findByID(id);
    }

    public void delete(int id){
        reviewRepositoryBD.delete(id);
    }
}