package com.Beans;
import org.springframework.stereotype.Component;

/**
 * Created by VanHelsing on 10.05.2016.
 */
public class TransformationBean {

    public String makeUpperCase(String body) {

        String transformedBody = body.toUpperCase();

        return transformedBody;

    }

}