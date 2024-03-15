package org.example.repositories;

import org.example.main.CartItem;
import org.example.main.Patterns.Observer.CartItemObservable;
import org.example.main.Patterns.Observer.OrderObserver;

import java.util.ArrayList;
import java.util.List;

public class CartItemRepository implements CartItemObservable {
    private List<CartItem> cartItems = new ArrayList<>();
    private OrderObserver observer;

    public CartItemRepository(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public List<CartItem> findAll(){
        return cartItems;
    }

    public void save(CartItem cartItem){
        for(CartItem existingCartItem : cartItems){
            if(existingCartItem.getBook().equals(cartItem.getBook())){
                existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
                return;
            }
        }
        cartItems.add(cartItem);
    }

    public void update(CartItem cartItem){
        for(int i=0; i<cartItems.size();i++){
            CartItem existingCartItem = cartItems.get(i);
            if(existingCartItem.getBook().equals(cartItem.getBook())){
                cartItems.set(i,cartItem);
                notifyObserver("Updated cart item", existingCartItem);
                return;
            }
        }
    }

    public void delete(CartItem cartItem){
        cartItems.removeIf(existingCartItem -> existingCartItem.getBook().equals(cartItem.getBook()) );
    }


    @Override
    public void notifyObserver(String event, CartItem cartItem) {
        observer.update_event(event, cartItem);
    }

    @Override
    public void notify_observer(String event, int id, int quantity){}
}
