package com.routes.requestProcessor;

import com.database.projectDB.model.Request;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Johannes on 19.05.2016.
 */
@Service
public class ProcessRequest implements Processor {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessRequest.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        Request request = exchange.getIn().getBody(Request.class);
        exchange.getOut().setBody(request);
    }

}