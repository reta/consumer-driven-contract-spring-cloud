package com.example.order;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMessageVerifier
@AutoConfigureStubRunner(ids = "com.example:order-service-messaging-contract-tests:+:stubs", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class OrderMessagingContractTest {
    @Autowired private MessageVerifier<Message<?>> verifier;
    @Autowired private StubFinder stubFinder;

    @Test
    public void testOrderConfirmed() throws Exception {
        stubFinder.trigger("order");
        
        final Message<?> message = verifier.receive("orders");
        assertThat(message, notNullValue());
        assertThat(message.getPayload(), isJson(
            allOf(List.of(
                withJsonPath("$.orderId"),
                withJsonPath("$.paymentId"),
                withJsonPath("$.amount"),
                withJsonPath("$.street"),
                withJsonPath("$.city"),
                withJsonPath("$.state"),
                withJsonPath("$.zip"),
                withJsonPath("$.country")
            ))));
    }
}