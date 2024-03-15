package org.example.main;

public class Review {
    private int review_id;
    private int stars_number;
    private String feedback;
    private int book_id;
    private String date;

    public Review(int review_id, int stars_number, String feedback, int book_id, String date) {
        this.review_id = review_id;
        this.stars_number = stars_number;
        this.feedback = feedback;
        this.book_id = book_id;
        this.date = date;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public int getStars_number() {
        return stars_number;
    }

    public void setStars_number(int stars_number) {
        this.stars_number = stars_number;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
