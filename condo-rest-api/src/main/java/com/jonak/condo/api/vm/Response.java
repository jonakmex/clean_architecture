package com.jonak.condo.api.vm;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public abstract class Response {
    public HttpStatus httpStatus;
}
