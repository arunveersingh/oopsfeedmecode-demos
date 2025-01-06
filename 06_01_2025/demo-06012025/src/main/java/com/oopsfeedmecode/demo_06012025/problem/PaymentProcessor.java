package com.oopsfeedmecode.demo_06012025.problem;

import org.springframework.stereotype.Component;

@Component
public class PaymentProcessor {
    public void process(Order order) {
        // Logic to process payment
        System.out.println("Processing payment for order: " + order.getId());
    }
}
