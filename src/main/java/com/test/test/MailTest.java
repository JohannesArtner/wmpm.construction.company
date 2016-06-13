import com.routes.offerProcessor.processors.IncomingMailProcessor;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.testng.CamelTestSupport;
import org.h2.store.DataHandler;
import org.junit.Test;

import javax.activation.FileDataSource;
import java.util.Map;

/**
 * Created by Johannes on 13.06.2016.
 */
public class MailTest extends CamelTestSupport {

        private String subject = "Test Camel Mail Route";

        @Test
        public void testSendAndReceiveMailWithAttachments() throws Exception {
            CamelContext context1 = context();
            Endpoint endpoint = context1.getEndpoint("imaps://imap.gmail.com?username=offerManagementConstructionCom@gmail.com&password=supersafepw");

            Exchange exchange = endpoint.createExchange();
            Message in = exchange.getIn();
            in.setBody("Hello World");
            //in.addAttachment("message1.xml", new DataHandler(new FileDataSource("src/data/message1.xml")));
            //in.addAttachment("message2.xml", new DataHandler(new FileDataSource("src/data/message2.xml")));

            Producer producer = endpoint.createProducer();
            producer.start();
            producer.process(exchange);

            Thread.sleep(5000);

            // verify destination1
            MockEndpoint destination1 = getMockEndpoint("mock:destination1");
            destination1.expectedMessageCount(1);
            Exchange destination1Exchange = destination1.assertExchangeReceived(0);
            destination1.assertIsSatisfied();

            // plain text
            assertEquals("Hello World", destination1Exchange.getIn().getBody(String.class));

            producer.stop();
        }

        protected RouteBuilder createRouteBuilder() throws Exception {
            return new RouteBuilder() {
                public void configure() throws Exception {
                    context().setStreamCaching(true);
                    from("imaps://imap.gmail.com?username=offerManagementConstructionCom@gmail.com&password=supersafepw"
                            + "&delete=false&unseen=true&consumer.delay=10000")
                            .setHeader("subject", constant(subject))
                            .process(new IncomingMailProcessor())
                            .to("mock:destination1")
                            .end();
                }
            };
        }
    }

