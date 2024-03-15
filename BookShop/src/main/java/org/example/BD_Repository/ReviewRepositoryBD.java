package org.example.BD_Repository;

import org.example.SqlServer;
import org.example.main.Patterns.Singelton.ReviewRepositorySingelton;
import org.example.main.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepositoryBD implements ReviewRepositorySingelton {
    private static ReviewRepositoryBD instance;
    private SqlServer sqlServer;

    private ReviewRepositoryBD(SqlServer sqlServer) {
        this.sqlServer = sqlServer;
    }

    public static ReviewRepositoryBD getInstance(SqlServer sqlServer) {
        if (instance == null) {
            instance = new ReviewRepositoryBD(sqlServer);
        }
        return instance;
    }

    @Override
    public void reset() {}

    public void saveIntoDB(Review review){
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO labor.Reviews(ReviewId, StarsNumber, Feedback, BookId, Date) VALUES (?,?,?,?,?)")){

                preparedStatement.setInt(1,review.getReview_id());
                preparedStatement.setInt(2, review.getStars_number());
                preparedStatement.setString(3,review.getFeedback());
                preparedStatement.setInt(4,review.getBook_id());
                preparedStatement.setString(5,review.getDate());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Review createReviewFromResultSet(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("ReviewId");
        int starnumber = resultSet.getInt("StarsNumber");
        String feedback = resultSet.getString("Feedback");
        int bookid = resultSet.getInt("BookId");
        String date = resultSet.getString("Date");

        Review review = new Review(id,starnumber,feedback,bookid,date);
        return review;
    }

    public List<Review> loadFromDB(){
        List<Review> result = new ArrayList<>();
        try (Connection connection = sqlServer.connect();
             Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM labor.Reviews";
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    result.add(createReviewFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public Review findByID(int id){
//        List<Review> allReviews = loadFromDB();
//        Review found = null;
//        for(Review review:allReviews){
//            if(review.getReview_id() == id)
//                found = review;
//        }
//
//        return found;
//    }

    public Review findByID(int id){
        String sql = "SELECT * FROM labor.Reviews WHERE ReviewId = ?";
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createReviewFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void delete(int id){
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM labor.Reviews WHERE ReviewId = ?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
