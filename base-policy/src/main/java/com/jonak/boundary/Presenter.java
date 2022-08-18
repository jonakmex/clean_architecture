package com.jonak.boundary;

import reactor.core.publisher.Mono;

public interface Presenter {
    void callback(Mono<Response> response);
}
