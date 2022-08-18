package com.jonak.condo.admin.interaction;

import com.jonak.boundary.Interaction;
import com.jonak.boundary.Presenter;
import com.jonak.boundary.Request;
import com.jonak.boundary.Response;
import com.jonak.condo.admin.boundary.HelloWorldRequest;
import com.jonak.condo.admin.boundary.HelloWorldResponse;
import lombok.Data;
import reactor.core.publisher.Mono;

import java.util.Map;

@Data
public class HelloWorldInteraction implements Interaction {
    @Override
    public void execute(Request request, Presenter presenter) {
        Map<String,String> errors = request.validate();
        if(!errors.isEmpty() && presenter != null) {
            presenter.callback(Mono.just(Response.makeFail(errors)));
            return;
        }

        HelloWorldRequest helloWorldRequest = (HelloWorldRequest) request;

        HelloWorldResponse helloWorldResponse = new HelloWorldResponse();
        helloWorldResponse.success = Boolean.TRUE;
        helloWorldResponse.greet = "Hello "+helloWorldRequest.name;

        if(presenter != null)
            presenter.callback(Mono.just(helloWorldResponse));

    }
}
