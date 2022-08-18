package com.jonak.boundary;

import lombok.Data;

import java.util.Map;

@Data
public class Response {
    public Boolean success;
    public Map<String,String> errors;

    public static Response makeFail(Map<String,String> errors){
        Response response = new Response();
        response.success = Boolean.FALSE;
        response.errors = errors;
        return response;
    }
}
