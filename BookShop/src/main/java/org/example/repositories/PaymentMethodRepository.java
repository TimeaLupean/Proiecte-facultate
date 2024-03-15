package org.example.repositories;

import org.example.main.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodRepository {

    private List<PaymentMethod> paymentMethods = new ArrayList<>();

    public PaymentMethodRepository(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public PaymentMethod findById(int targetPaymentId) {
        for (PaymentMethod paymentMethod : paymentMethods) {
            if (paymentMethod.getPayment_id() == targetPaymentId) {
                return paymentMethod;
            }
        }
        System.out.println("Could not find payment method with Id:" + targetPaymentId);
        return null;
    }

    public List<PaymentMethod> findAll() {
        if (paymentMethods.isEmpty()) {
            System.out.println("No payment method was found");
            return null;
        }
        return paymentMethods;
    }

    public boolean save(PaymentMethod paymentMethod) {
        boolean saved = false;
        for (PaymentMethod item : paymentMethods) {
            if (paymentMethod.getPayment_id() == item.getPayment_id()) {
                System.out.println("Order already exists");
                return saved;
            }

        }
        paymentMethods.add(paymentMethod);
        for (PaymentMethod item : paymentMethods) {
            if (paymentMethod.getPayment_id() == item.getPayment_id())
                saved = true;
        }

        return saved;
    }

    public boolean update(PaymentMethod updatedPaymentMethod) {
        boolean updated = false;
        for (PaymentMethod paymentMethod : paymentMethods) {
            if (paymentMethod.getPayment_id() == updatedPaymentMethod.getPayment_id()) {
                //paymentMethod.setType(updatedPaymentMethod.getType());
                paymentMethod.setStatus(updatedPaymentMethod.getStatus());
                updated = true;
                break;
            }
        }
        if (updated == false)
            System.out.println("Payment method was not updated");
        return updated;
    }

    public boolean delete(int targetPaymentId) {
        boolean deleted = false;
        PaymentMethod paymentMethodToRemove = null;
        for (PaymentMethod paymentMethod : paymentMethods) {
            if (paymentMethod.getPayment_id() == targetPaymentId) {
                paymentMethodToRemove = paymentMethod;
                break;
            }
        }

        if (paymentMethodToRemove == null) {
            System.out.println("Payment method does not exist");
            deleted = false;
        }

        if (paymentMethodToRemove != null) {
            paymentMethods.remove(paymentMethodToRemove);
            deleted = true;

        }
        return deleted;
    }


}