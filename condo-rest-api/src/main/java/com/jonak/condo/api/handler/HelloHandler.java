package com.jonak.condo.api.handler;

import com.jonak.boundary.Interaction;
import com.jonak.boundary.Request;
import com.jonak.condo.api.presenter.HelloWorldPresenter;
import com.jonak.factory.RequestFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j(topic="HelloHandler")
@AllArgsConstructor
public class HelloHandler {

    private final Interaction helloWorldInteraction;
    private final RequestFactory requestFactory;

    public Mono<ServerResponse> hello(ServerRequest serverRequest){
        String name = serverRequest.pathVariable("name");
        Request request = requestFactory.make("HelloWorldRequest", Stream.of(
                new AbstractMap.SimpleImmutableEntry<>("name", name))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

        HelloWorldPresenter helloWorldPresenter = new HelloWorldPresenter();
        helloWorldInteraction.execute(request,helloWorldPresenter);

        return helloWorldPresenter.getServerResponse();
    }

    public  Mono<ServerResponse> health(ServerRequest serverRequest) {
        return ServerResponse.ok().build();
    }
}
