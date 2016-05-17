package com.Beans;

import org.apache.camel.Exchange;
import org.apache.camel.language.XPath;

/**
 * Created by VanHelsing on 17.05.2016.
 */
public class MyNormalizer {
    public void senderToCustomer(Exchange exchange, @XPath("/sender/name/text()") String name) {
        exchange.getOut().setBody(createPerson(name));
    }

    public void reqeustToTopic(Exchange exchange, @XPath("/request/@name") String name) {
        exchange.getOut().setBody(createPerson(name));
    }

    private String createPerson(String name) {
        return "<person name=\"" + name + "\"/>";
    }
}