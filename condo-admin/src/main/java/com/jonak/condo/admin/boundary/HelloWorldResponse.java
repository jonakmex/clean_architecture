package com.jonak.condo.admin.boundary;

import com.jonak.boundary.Response;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class HelloWorldResponse extends Response {
    public String greet;
}
