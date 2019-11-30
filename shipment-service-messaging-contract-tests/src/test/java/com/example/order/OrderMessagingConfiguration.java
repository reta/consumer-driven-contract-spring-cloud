package com.example.order;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.PollableChannel;

@SpringBootConfiguration
public class OrderMessagingConfiguration {
    @Bean
    PollableChannel orders() {
        return MessageChannels.queue().get();
    }
}
