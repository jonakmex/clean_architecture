package com.jonak.boundary;

import lombok.Data;

import java.util.Map;

@Data
public abstract class Request {
    
    public abstract Map<String,String> validate();
}
