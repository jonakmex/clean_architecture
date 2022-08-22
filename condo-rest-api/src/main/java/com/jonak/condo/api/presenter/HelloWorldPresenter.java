package com.jonak.condo.api.presenter;

import com.jonak.boundary.Presenter;

import com.jonak.condo.api.vm.HelloWorldResponse;
import com.jonak.condo.api.vm.Response;
import com.jonak.condo.api.vm.SystemErrorResponse;
import com.jonak.condo.api.vm.ValidationErrorResponse;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Data
public class HelloWorldPresenter implements Presenter {

    private static final Logger log = LoggerFactory.getLogger(HelloWorldPresenter.class);

    @Setter(AccessLevel.PRIVATE)
    private Mono<ServerResponse> serverResponse;

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private Response responseVm;

    @Override
    public void callback(Mono<com.jonak.boundary.Response> responsePublisher) {
        serverResponse = responsePublisher.flatMap(response -> makeServerResponse(response));
    }

    private Mono<ServerResponse> makeServerResponse(com.jonak.boundary.Response response) {
        if(response.success)
            responseVm = makeSuccessResponse(response);
        else
            responseVm = makeFailureResponse(response);

        return ServerResponse.status(responseVm.httpStatus)
                .body(Mono.just(responseVm),Response.class);
    }

    private Response makeSuccessResponse(com.jonak.boundary.Response response) {
        HelloWorldResponse helloWorldResponseVm = new HelloWorldResponse();
        helloWorldResponseVm.httpStatus = HttpStatus.OK;
        helloWorldResponseVm.greet = ((com.jonak.condo.admin.boundary.HelloWorldResponse) response).greet +" from "+getHostAddress();
        return helloWorldResponseVm;
    }

    private Response makeFailureResponse(com.jonak.boundary.Response response) {
        if(isValidationError(response))
            return makeValidationFailResponse(response);
        else
            return makeSystemFailResponse(response);
    }

    private boolean isValidationError(com.jonak.boundary.Response response) {
        return response.validationErrors!=null && !response.validationErrors.isEmpty();
    }

    private Response makeSystemFailResponse(com.jonak.boundary.Response response) {
        SystemErrorResponse systemErrorResponse = new SystemErrorResponse();
        systemErrorResponse.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        systemErrorResponse.systemErrors = response.systemErrors;
        return systemErrorResponse;
    }

    private Response makeValidationFailResponse(com.jonak.boundary.Response response) {
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
        validationErrorResponse.httpStatus = HttpStatus.BAD_REQUEST;
        validationErrorResponse.validationErrors = response.validationErrors;
        return validationErrorResponse;
    }

    private String getHostAddress() {
        String hostaddress = "";
        try {
            hostaddress =  InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("Error while fetching hostaddress:", e);
        }
        return hostaddress;}
}
