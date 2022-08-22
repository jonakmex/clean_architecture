package com.jonak.condo.api;

import com.jonak.condo.api.handler.HelloHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
@Slf4j(topic="CondoRouter")
public class CondoRouter {
    @Bean
    public RouterFunction<ServerResponse> route(HelloHandler helloHandler) {
        return RouterFunctions.route(
                GET("/"),helloHandler::health)
                    .andRoute(GET("/hello/{name}"),helloHandler::hello);
    }
}
