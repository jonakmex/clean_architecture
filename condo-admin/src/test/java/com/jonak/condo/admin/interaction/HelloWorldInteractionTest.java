package com.jonak.condo.admin.interaction;

import com.jonak.boundary.Interaction;
import com.jonak.boundary.Request;
import com.jonak.condo.admin.boundary.HelloWorldResponse;
import com.jonak.condo.admin.factory.RequestFactoryImpl;
import com.jonak.factory.InteractionFactory;
import com.jonak.factory.RequestFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.test.StepVerifier;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class HelloWorldInteractionTest {

    private static InteractionFactory interactionFactory;
    private static RequestFactory requestFactory;
    private static final Logger logger = LoggerFactory.getLogger("HelloWorldInteractionTest");

    private static Interaction helloWorldInteraction;

    @BeforeAll
    public static void setup(){
        interactionFactory = name -> new HelloWorldInteraction();
        requestFactory = new RequestFactoryImpl("com.jonak.condo.admin.boundary");
        helloWorldInteraction = interactionFactory.make("HelloWorldInteraction");
    }

    @Test
    public void should_execute_successfully(){
        String name = "Jonathan";

        Request request = requestFactory.make("HelloWorldRequest", Stream.of(
                new AbstractMap.SimpleImmutableEntry<>("name", name))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        helloWorldInteraction.execute(request,responsePublisher -> {
            StepVerifier.create(responsePublisher)
                    .expectNextMatches(response -> {
                        assertTrue(response.success);
                        assertTrue(response.errors == null);

                        assertTrue(response instanceof HelloWorldResponse);
                        HelloWorldResponse helloWorldResponse = (HelloWorldResponse) response;
                        assertEquals("Hello Jonathan",helloWorldResponse.greet);
                        return true;
                    })
                    .expectComplete()
                    .verify();
        });

    }

    @Test
    public void should_execute_with_too_short_exception(){
        String name = "J";

        Request request = requestFactory.make("HelloWorldRequest", Stream.of(
                new AbstractMap.SimpleImmutableEntry<>("name", name))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

        helloWorldInteraction.execute(request,responsePublisher -> {
            StepVerifier.create(responsePublisher)
                    .expectNextMatches(response -> {
                        assertFalse(response.success);
                        assertFalse(response.errors.isEmpty());
                        return true;
                    })
                    .expectComplete()
                    .verify();
        });

    }

}
