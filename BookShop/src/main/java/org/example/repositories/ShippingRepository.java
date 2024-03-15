package org.example.repositories;

import org.example.main.Shipping;

import java.util.ArrayList;
import java.util.List;

public class ShippingRepository {

    private List<Shipping> shippings = new ArrayList<>();

    public ShippingRepository(List<Shipping> shippings) {
        this.shippings = shippings;
    }

    public Shipping findById(int targetShippingId) {
        for (Shipping shipping : shippings) {
            if (shipping.getShipping_id() == targetShippingId) {
                return shipping;
            }
        }
        System.out.println("Could not find Shipping with Id:" + targetShippingId);
        return null;
    }

    public List<Shipping> findAll() {
        if (shippings.isEmpty()) {
            System.out.println("There are no shippings");
            return null;
        }
        return shippings;
    }

    public boolean save(Shipping shipping) {
        boolean saved = false;
        for (Shipping item : shippings) {
            if (shipping.getShipping_id() == item.getShipping_id()) {
                System.out.println("shipping already exists");
                return saved;
            }

        }
        shippings.add(shipping);
        for (Shipping item : shippings) {
            if (shipping.getShipping_id() == item.getShipping_id())
                saved = true;
        }
        return saved;
    }

    public boolean update(Shipping updatedShipping) {
        boolean updated = false;
        for (Shipping shipping : shippings) {
            if (shipping.getShipping_id() == updatedShipping.getShipping_id()) {
                shipping.setAddress(updatedShipping.getAddress());
                shipping.setShipping_method(updatedShipping.getShipping_method());
                updated = true;
                break;

            }
        }
        if (updated == false)
            System.out.println("Shipment was not updated");

        return updated;
    }

    public boolean delete(int targetShippingId) {
        boolean deleted = false;
        Shipping shippingToRemove = null;
        for (Shipping shipping : shippings) {
            if (shipping.getShipping_id() == targetShippingId) {
                shippingToRemove = shipping;
                break;
            }
        }
        if (shippingToRemove != null) {
            shippings.remove(shippingToRemove);
            deleted = true;
        }

        if (shippingToRemove == null) {
            System.out.println("Order does not exist");
            deleted = false;
        }

        return deleted;
    }

}
