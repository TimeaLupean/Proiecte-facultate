package org.example.controllers;

import org.example.main.Shipping;
import org.example.repositories.ShippingRepository;

import java.util.List;

public class ShippingController {
    private ShippingRepository shippingRepository;

    public ShippingController(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    public void createShipping(int shippingId, String address, String shippingMethod) {
        Shipping shipping = new Shipping(shippingId, address, shippingMethod);
        shippingRepository.save(shipping);
    }

    public Shipping findShippingById(int shippingId) {
        return shippingRepository.findById(shippingId);
    }

    public List<Shipping> viewAllShippings() {
        return shippingRepository.findAll();
    }

    public void updateShipping(int shippingId, String address, String shippingMethod) {
        Shipping updatedShipping = new Shipping(shippingId, address, shippingMethod);
        shippingRepository.update(updatedShipping);
    }

    public void deleteShipping(int shippingId) {
        shippingRepository.delete(shippingId);
    }
}
