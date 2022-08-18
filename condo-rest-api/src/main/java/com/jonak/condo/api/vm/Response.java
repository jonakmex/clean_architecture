package com.jonak.condo.api.vm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
@JsonIgnoreProperties({ "httpStatus" })
public abstract class Response {
    public HttpStatus httpStatus;
}
