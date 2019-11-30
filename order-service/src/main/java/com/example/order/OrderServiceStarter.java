package com.example.order;

import java.math.BigDecimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.order.config.OrderMessagingConfiguration;
import com.example.order.event.Address;

@SpringBootApplication
public class OrderServiceStarter {
    public static void main(String[] args) throws Exception {
        try (var ctx = new SpringApplication(OrderMessagingConfiguration.class).run(args)) {
            final OrderService service = ctx.getBean(OrderService.class);
            
            service.createOrder(
                "4899097f-00d9-32d5-5e93-fe276bf6cd68",
                "e2d548c5-e1bf-407f-aed4-c973dc753e3e",
                new BigDecimal(243.30d),
                new Address()
                    .withStreet("1203 Westmisnter Blvrd")
                    .withCity("Westminster")
                    .withCountry("USA")
                    .withState("MI")
                    .withZip("92239")
                );
        }
    }
}
