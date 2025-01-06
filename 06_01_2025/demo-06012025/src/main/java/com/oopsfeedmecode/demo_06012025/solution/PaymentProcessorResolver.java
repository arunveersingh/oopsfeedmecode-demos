package com.oopsfeedmecode.demo_06012025.solution;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public abstract class PaymentProcessorResolver {

    @Lookup("creditCardPaymentProcessor")
    public abstract IPaymentProcessor getCreditCardProcessor();

    @Lookup("paypalPaymentProcessor")
    public abstract IPaymentProcessor getPaypalProcessor();
}
