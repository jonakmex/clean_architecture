package com.jonak.condo.admin.boundary;

import com.jonak.boundary.Request;
import com.jonak.condo.admin.factory.RequestFactoryImpl;
import com.jonak.factory.RequestFactory;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class RequestFactoryTest {
    @Test
    public void should_create_hello_request(){
        String name = "Jonathan";
        RequestFactory requestFactory = new RequestFactoryImpl("com.jonak.condo.admin.boundary");
        Request request = requestFactory.make("HelloWorldRequest", Stream.of(
                new AbstractMap.SimpleImmutableEntry<>("name", name))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

        assertTrue(request instanceof HelloWorldRequest);
        HelloWorldRequest helloWorldRequest = (HelloWorldRequest) request;
        assertEquals(name,helloWorldRequest.name);
    }

    @Test
    public void should_validate_too_short_hello_request(){
        String name = "X";
        RequestFactory requestFactory = new RequestFactoryImpl("com.jonak.condo.admin.boundary");
        Request request = requestFactory.make("HelloWorldRequest", Stream.of(
                new AbstractMap.SimpleImmutableEntry<>("name", name))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

        assertTrue(request instanceof HelloWorldRequest);
        HelloWorldRequest helloWorldRequest = (HelloWorldRequest) request;
        Map<String,String> errors = helloWorldRequest.validate();
        assertFalse(errors.isEmpty());
        assertEquals(errors.get("name"),"MSG_ERR_001");
    }

    @Test
    public void should_vaidate_too_large_hello_request(){
        String name = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
        RequestFactory requestFactory = new RequestFactoryImpl("com.jonak.condo.admin.boundary");
        Request request = requestFactory.make("HelloWorldRequest", Stream.of(
                new AbstractMap.SimpleImmutableEntry<>("name", name))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

        assertTrue(request instanceof HelloWorldRequest);
        HelloWorldRequest helloWorldRequest = (HelloWorldRequest) request;
        Map<String,String> errors = helloWorldRequest.validate();
        assertFalse(errors.isEmpty());
        assertEquals(errors.get("name"),"MSG_ERR_002");
    }
}
