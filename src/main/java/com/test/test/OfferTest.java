import com.Application;
import com.database.employeeDB.model.SpecializationType;
import com.database.projectDB.model.Request;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@SpringBootApplication
public class OfferTest implements CommandLineRunner {

    @Autowired
    private CamelContext context;

    private ConsumerTemplate consumer;
    private ProducerTemplate producer;

    @EndpointInject(ref = "result")
    private MockEndpoint mock;

    public static void main(String[] args) {
        SpringApplication.run(OfferTest.class);
    }

    @Autowired
    private CamelContext camelContext;

    @Override
    public void run(String ... args) throws Exception {
        testConsumeTemplate();
    }

    public void testConsumeTemplate() throws Exception {

        consumer = context.createConsumerTemplate();
        producer = context.createProducerTemplate();

        mock.expectedBodiesReceived("Hello World");
        producer.sendBody("seda:start", "Hello World");
        String body = consumer.receiveBody("seda:start", String.class);
        assertEquals("Hello World", body);

        producer.sendBody("seda:foo", body);

        mock.assertIsSatisfied();
    }

}