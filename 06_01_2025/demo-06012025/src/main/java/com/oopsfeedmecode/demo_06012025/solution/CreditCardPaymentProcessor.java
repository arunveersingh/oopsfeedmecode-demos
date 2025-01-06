package com.oopsfeedmecode.demo_06012025.solution;

import com.oopsfeedmecode.demo_06012025.problem.Order;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("credit-card")
public class CreditCardPaymentProcessor implements IPaymentProcessor {
    @Override
    public void process(Order order) {
        // Logic for credit card payment
        System.out.println("Processing credit card payment for order: " + order.getId());
    }
}
