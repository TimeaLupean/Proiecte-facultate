package org.example.main;

public class Shipping {
    private int shipping_id;
    private String address;
    private String shipping_method;

    public Shipping(int shipping_id, String address, String shipping_method) {
        this.shipping_id = shipping_id;
        this.address = address;
        this.shipping_method = shipping_method;
    }

    public int getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(int shipping_id) {
        this.shipping_id = shipping_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShipping_method() {
        return shipping_method;
    }

    public void setShipping_method(String shipping_method) {
        this.shipping_method = shipping_method;
    }
}
