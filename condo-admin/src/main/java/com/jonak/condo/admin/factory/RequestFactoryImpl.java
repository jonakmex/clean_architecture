package com.jonak.condo.admin.factory;

import com.jonak.boundary.Request;
import com.jonak.factory.RequestFactory;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Data
@AllArgsConstructor
public class RequestFactoryImpl implements RequestFactory {
    private String basePackage;

    @Override
    public Request make(String name, Map<String, Object> params) {
        try {
            Class requestClass = Class.forName(basePackage+"."+name);
            Object request = requestClass.getDeclaredConstructor().newInstance();
            for(Field field : request.getClass().getDeclaredFields()){
                if(params.containsKey(field.getName()))
                    field.set(request,params.get(field.getName()));
            }
            return (Request) request;
        } catch (ClassNotFoundException e) {
            return null;
        } catch (InstantiationException e) {
            return null;
        } catch (InvocationTargetException e) {
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }
}
