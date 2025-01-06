package com.oopsfeedmecode.demo_06012025.solution;

import com.oopsfeedmecode.demo_06012025.problem.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    IPaymentProcessor mockProcessor;
    @Autowired
    @InjectMocks
    public OrderService orderService;

    @Test
    void testOrderProcessingWithMock() {
        Order order = new Order("123");
        orderService.processOrder(order);

        verify(mockProcessor).process(order); // Behavior verification
    }
}
