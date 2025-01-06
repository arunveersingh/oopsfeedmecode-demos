package com.oopsfeedmecode.demo_06012025.solution;

import com.oopsfeedmecode.demo_06012025.problem.PaymentProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PaymentProcessorFactory {

    private final Map<String, IPaymentProcessor> processors;

    @Autowired
    public PaymentProcessorFactory(ApplicationContext context) {
        Map<String, IPaymentProcessor> allProcessors = context.getBeansOfType(IPaymentProcessor.class);
        this.processors = allProcessors.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // Use bean name as key
                        Map.Entry::getValue // Use the bean instance as value
                ));
    }

    public IPaymentProcessor getProcessor(String type) {
        return Optional.ofNullable(processors.get(type))
                .orElseThrow(() -> new IllegalArgumentException("No processor found for type: " + type));
    }
}