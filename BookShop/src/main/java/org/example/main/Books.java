package org.example.main;


import org.example.main.Patterns.Decorator.BooksDecorator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

public class Books implements BooksDecorator {
    private int book_id;
    private String title;
    private int publishing_year;
    private Author author;
    private int price;
    private Category category;

    public Books(int book_id, String title, int publishing_year, Author author, int price, Category category) {
        this.book_id = book_id;
        this.title = title;
        this.publishing_year = publishing_year;
        this.author = author;
        this.price = price;
        this.category = category;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishing_year() {
        return publishing_year;
    }

    public void setPublishing_year(int publishing_year) {
        this.publishing_year = publishing_year;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public static String getCurrentDayOfWeek() {
        LocalDate currentDate = LocalDate.now();

        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();

        Locale locale = Locale.getDefault();
        String dayOfWeekString = dayOfWeek.getDisplayName(TextStyle.FULL, locale);

        return dayOfWeekString;
    }
    public void getDiscountedPrice() {
        String dayOfWeek = getCurrentDayOfWeek();
        if (dayOfWeek.equals("Friday")) {
            int discountedprice =  (int) (price * 0.9);
            setPrice(discountedprice);
        } else {
            setPrice(price);
        }
    }

    @Override
    public int getPrice() {
        String dayOfWeek = getCurrentDayOfWeek();
        if (dayOfWeek.equals("Friday")) {
            int discountedprice = (int) (price * 0.9);
            return discountedprice;
        }
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Books{" +
                "book_id=" + book_id +
                ", title='" + title + '\'' +
                ", publishing_year=" + publishing_year +
                ", author=" + author +
                ", price=" + price +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Books books = (Books) obj;
        return book_id == books.book_id &&
                publishing_year == books.publishing_year &&
                price == books.price &&
                Objects.equals(title, books.title) &&
                Objects.equals(author, books.author) &&
                Objects.equals(category, books.category);
    }

}
