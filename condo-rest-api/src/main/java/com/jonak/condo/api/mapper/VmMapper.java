package com.jonak.condo.api.mapper;

import com.jonak.boundary.Response;
import com.jonak.condo.api.vm.HelloWorldResponse;

public class VmMapper {
    public static HelloWorldResponse makeHelloWorldResponse(Response response){
        com.jonak.condo.admin.boundary.HelloWorldResponse helloWorldResponse = (com.jonak.condo.admin.boundary.HelloWorldResponse) response;
        return new HelloWorldResponse(helloWorldResponse.greet);
    }
}
