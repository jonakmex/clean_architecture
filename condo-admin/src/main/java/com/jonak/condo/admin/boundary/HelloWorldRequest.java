package com.jonak.condo.admin.boundary;

import com.jonak.boundary.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class HelloWorldRequest extends Request {
    public String name;

    @Override
    public Map<String, String> validate() {
        Map<String,String> errors = new HashMap<>();
        if(name == null || name.length() < 3)
            errors.put("name","MSG_ERR_001");
        else if(name.length() > 50)
            errors.put("name","MSG_ERR_002");

        return errors;
    }
}
