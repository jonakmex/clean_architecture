package com.jonak.condo.admin.interaction;

import com.jonak.boundary.Interaction;
import com.jonak.boundary.Presenter;
import com.jonak.boundary.Request;
import com.jonak.boundary.Response;
import com.jonak.condo.admin.boundary.HelloWorldRequest;
import com.jonak.condo.admin.boundary.HelloWorldResponse;
import lombok.Data;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Data
public class HelloWorldInteraction implements Interaction {
    @Override
    public void execute(Request request, Presenter presenter) {
        Map<String,String> validationErrors = request.validate();
        if(!validationErrors.isEmpty() && presenter != null) {
            presenter.callback(Mono.just(Response.makeValidationFail(validationErrors)));
            return;
        }

        HelloWorldRequest helloWorldRequest = (HelloWorldRequest) request;

        if(helloWorldRequest.name.equals("poison")){
            Map<String,String> systemErrors = new HashMap<>();
            systemErrors.put("RESOURCE_NOT_AVAILABLE","This is the System Error Stacktrace");
            if(presenter != null) {
                presenter.callback(Mono.just(Response.makeSystemFail(systemErrors)));
                return;
            }
        }


        HelloWorldResponse helloWorldResponse = new HelloWorldResponse();
        helloWorldResponse.success = Boolean.TRUE;
        helloWorldResponse.greet = "Hello "+helloWorldRequest.name;

        if(presenter != null)
            presenter.callback(Mono.just(helloWorldResponse));

    }
}
