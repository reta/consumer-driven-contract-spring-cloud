package com.example.order;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import com.example.order.event.Address;
import com.example.order.event.OrderConfirmed;

@Service
public class OrderService {
    @Autowired private MessageChannel orders;
    
    public void createOrder(String orderId, String paymentId, 
            BigDecimal amount, Address address) {
        final OrderConfirmed order = new OrderConfirmed();
        order.setOrderId(UUID.fromString(orderId));
        order.setPaymentId(UUID.fromString(paymentId));
        order.setAmount(amount);
        order.setStreet(address.getStreet());
        order.setCity(address.getCity());
        order.setCountry(address.getCountry());
        order.setState(address.getState());
        order.setZip(address.getZip());

        orders.send(
            MessageBuilder
                .withPayload(order)
                .setHeader("Content-Type", "application/json")
                .build());
    }

}
