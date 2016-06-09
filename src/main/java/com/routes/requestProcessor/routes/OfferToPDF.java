package com.routes.requestProcessor.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.fop.FopConstants;

/**
 * Created by Johannes on 09.06.2016.
 */
public class OfferToPDF extends RouteBuilder {
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
}
