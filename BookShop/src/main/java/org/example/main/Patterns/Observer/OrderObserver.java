package org.example.main.Patterns.Observer;

import org.example.main.CartItem;

public interface OrderObserver {
    void update_event(String event, CartItem cartItem);
    void update_event2(String event, int id, int quantity);
}
