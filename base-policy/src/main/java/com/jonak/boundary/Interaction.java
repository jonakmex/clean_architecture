package com.jonak.boundary;

import reactor.core.publisher.Mono;

public interface Interaction {
    Mono<Response> execute(Request request);
}
