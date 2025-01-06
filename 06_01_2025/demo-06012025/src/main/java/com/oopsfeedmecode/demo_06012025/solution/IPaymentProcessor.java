package com.oopsfeedmecode.demo_06012025.solution;

import com.oopsfeedmecode.demo_06012025.problem.Order;

public interface IPaymentProcessor {
    void process(Order order);
}