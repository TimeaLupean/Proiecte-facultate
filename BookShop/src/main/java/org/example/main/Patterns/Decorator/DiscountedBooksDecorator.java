package org.example.main.Patterns.Decorator;

public class DiscountedBooksDecorator implements BooksDecorator{
    private BooksDecorator decoratedBooks;
    private double discount;

    public DiscountedBooksDecorator(BooksDecorator decoratedBooks, double discount) {
        this.decoratedBooks = decoratedBooks;
        this.discount = discount;
    }

    @Override
    public int getPrice() {
        int discountedPrice = (int) (decoratedBooks.getPrice() * (1 - discount));
        return discountedPrice;
    }
}
