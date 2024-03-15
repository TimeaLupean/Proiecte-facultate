package org.example.controllers;
import org.example.main.Books;
import org.example.main.CartItem;
import org.example.main.Clients;
import org.example.repositories.CartItemRepository;

import java.util.List;

public class CartController {
    private CartItemRepository cartItemRepository;
    private Clients currentClient;

    public CartController(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public void createcartItem(Books book, int quantity) {
        CartItem cartItem = new CartItem(book,quantity);
        cartItemRepository.save(cartItem);
    }



    public List<CartItem> viewAllCartItems() {
        return cartItemRepository.findAll();
    }

    public void updatecartItem(Books book, int quantity) {
        CartItem updatedcartItem = new CartItem(book,quantity);
        cartItemRepository.update(updatedcartItem);
    }

    public void deletecartItem(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }


}
