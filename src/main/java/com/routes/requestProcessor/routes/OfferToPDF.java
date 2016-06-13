package com.routes.requestProcessor.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.fop.FopConstants;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Johannes on 09.06.2016.
 */
public class OfferToPDF extends RouteBuilder {


    @Autowired
    public CamelContext context;

    @Override
    public void configure() throws Exception {
        String readDir = "c:/Temp/camel/offerToPdf";

        from("direct:offerToPdf")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        final String body = exchange.getIn().getBody(String.class);
                        final String fileNameWithoutExtension = AppUtil.getFileNameWithoutExtension(exchange);
                        final String convertToXSLFOBody = AppUtil.getFilledXSLFO(body);
                        exchange.getIn().setBody(convertToXSLFOBody);
                        exchange.getIn().setHeader(Exchange.FILE_NAME, fileNameWithoutExtension + ".pdf");
                        exchange.getIn().setHeader(FopConstants.CAMEL_FOP_RENDER + "author", "The construction company");
                    }
                })
                .to("fop:application/pdf")
                .to("file://" + readDir);
    }

    public static void main(String[] args){
        try {
            OfferToPDF offerToPDF = new OfferToPDF();
            CamelContext context = new DefaultCamelContext();
            offerToPDF.context = context;
            //offerToPDF.configure();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
