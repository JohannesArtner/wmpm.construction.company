package com.routes.requestProcessor.routes;

import com.database.projectDB.model.Offer;
import com.routes.requestProcessor.processors.AppUtil;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.fop.FopConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Johannes on 09.06.2016.
 */
@Component
public class OfferToPDF extends RouteBuilder {


    @Autowired
    public CamelContext context;

    @Override
    public void configure() throws Exception {
        String readDir = "c:/Temp/camel/offerToPdf";

        errorHandler(deadLetterChannel("jms:queue:dead"));

        from("seda:offerToPdf")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        final Offer offer = (Offer) exchange.getIn().getBody();


                        String text;
                        text = "Hello Customer!\n We can make you the following Offer:\n";
                        text += offer.toString();

                        final String fileNameWithoutExtension = AppUtil.getFileNameWithoutExtension(exchange);
                        final String convertToXSLFOBody = AppUtil.getFilledXSLFO(text);
                        exchange.getIn().setBody(convertToXSLFOBody);
                        exchange.getIn().setHeader(Exchange.FILE_NAME, fileNameWithoutExtension + ".pdf");
                        exchange.getIn().setHeader(FopConstants.CAMEL_FOP_RENDER + "author", "The construction company");
                    }
                })
                .to("fop:application/pdf")
                .to("file://" + readDir);
    }
}
