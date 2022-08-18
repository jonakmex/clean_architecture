package com.jonak.factory;

import com.jonak.boundary.Request;

import java.util.Map;

public interface RequestFactory {
    Request make(String name, Map<String,Object> params);
}
