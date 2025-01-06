package com.oopsfeedmecode.demo_06012025.solution;

import com.oopsfeedmecode.demo_06012025.problem.Order;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("PayPal")
public class PayPalPaymentProcessor implements IPaymentProcessor {
    @Override
    public void process(Order order) {
        // Logic for PayPal payment
        System.out.println("Processing PayPal payment for order: " + order.getId());
    }
}