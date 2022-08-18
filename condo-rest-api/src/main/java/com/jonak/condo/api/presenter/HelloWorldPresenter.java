package com.jonak.condo.api.presenter;

import com.jonak.boundary.Presenter;
import com.jonak.boundary.Response;
import com.jonak.condo.api.vm.HelloWorldResponse;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Data
public class HelloWorldPresenter implements Presenter {

    @Setter(AccessLevel.PRIVATE)
    private Mono<ServerResponse> serverResponse;

    @Override
    public void callback(Mono<Response> responsePublisher) {
        serverResponse = responsePublisher.flatMap(response -> makeServerResponse(response));
    }

    private Mono<ServerResponse> makeServerResponse(Response response) {
        if(response.success)
            return makeSuccessResponse(response);
        else
            return makeFailureResponse(response);
    }

    private Mono<ServerResponse> makeSuccessResponse(Response response) {
        HelloWorldResponse helloWorldResponseVm = new HelloWorldResponse();
        helloWorldResponseVm.greet = ((com.jonak.condo.admin.boundary.HelloWorldResponse) response).greet;
        return ServerResponse.ok().body(Mono.just(helloWorldResponseVm),HelloWorldResponse.class);
    }

    private Mono<ServerResponse> makeFailureResponse(Response response) {
        return ServerResponse.badRequest().body(Mono.just(response.errors), HashMap.class);
    }
}
