package com.jonak.boundary;

import lombok.Data;

import java.util.Map;

@Data
public class Response {
    public Boolean success;
    public Map<String,String> validationErrors;
    public Map<String,String> systemErrors;

    public static Response makeValidationFail(Map<String,String> validationErrors){
        Response response = new Response();
        response.success = Boolean.FALSE;
        response.validationErrors = validationErrors;
        return response;
    }

    public static Response makeSystemFail(Map<String,String> systemErrors){
        Response response = new Response();
        response.success = Boolean.FALSE;
        response.systemErrors = systemErrors;
        return response;
    }
}
