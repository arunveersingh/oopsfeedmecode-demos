package com.oopsfeedmecode.demo_06012025.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OrderService {

    @Autowired
    private PaymentProcessor paymentProcessor;

    public void processOrder(Order order) {
        paymentProcessor.process(order);
    }
}












    /**
     *     If we need to switch to a different payment processor, we’ll have to modify the OrderService code.
     *     This violates the Open/Closed Principle by making OrderService dependent on specific implementations
     *     instead of being open to extension through abstractions.
     *     It also risks violating the Liskov Substitution Principle if different processors require special
     *     handling or introduce behavior not conforming to the shared contract.
     *
     *     Testability Issues: Imagine a situation if you and your peer are working on this requirement in parallel.
     *     You have to unit test this class; but Payment processor class is still not complete so your peer is hesitant
     *     committing it in the repo.
     *
     *     Reduced Extensibility: Adding features like multi-payment processors requires significant refactoring.
     */
