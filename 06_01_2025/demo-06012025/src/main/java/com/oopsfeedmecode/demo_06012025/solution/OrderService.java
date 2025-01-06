package com.oopsfeedmecode.demo_06012025.solution;

import com.oopsfeedmecode.demo_06012025.problem.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OrderService {
    @Autowired
    private IPaymentProcessor paymentProcessor;

    public void processOrder(Order order) {
        paymentProcessor.process(order);
    }
}















// how to inject 2 different implementations:
// 1. User profiles: spring.profiles.active=credit-card
// 2. PaymentProcessorFactory
// 3. Use Resolver
// private PaymentProcessor resolveProcessor(String paymentType) {
//        return switch (paymentType.toLowerCase()) {
//            case "creditcard" -> resolver.getCreditCardProcessor();
//            case "paypal" -> resolver.getPaypalProcessor();
//            default -> throw new IllegalArgumentException("Invalid payment type: " + paymentType);
//        };
//    }

