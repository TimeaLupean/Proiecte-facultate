package org.example.main;

import org.example.main.Patterns.Strategy.PaymentStrategy;

public class PaymentMethod {
    private int payment_id;
    private String status;
    private PaymentStrategy paymentStrategy;

    public PaymentMethod(int payment_id,  String status,PaymentStrategy paymentStrategy) {
        this.payment_id = payment_id;
        this.paymentStrategy=paymentStrategy;
        this.status = status;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment() {
        if (paymentStrategy != null) {
            paymentStrategy.processPayment();
            String paymentType = paymentStrategy.getPaymentType();
            System.out.println("Payment type: " + paymentType);
        } else {
            System.out.println("Please set a payment strategy before processing payment.");
        }
    }

}