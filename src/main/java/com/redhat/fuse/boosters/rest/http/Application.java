package com.redhat.fuse.boosters.rest.http;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.amqp.AMQPConnectionDetails;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Application {
	
	@Bean
	AMQPConnectionDetails securedAmqpConnection() {
	  return new AMQPConnectionDetails("amqp://broker-amq-amqp.fuse-demo.svc:5672", "amq-demo-user", "UYFaMmpp"); 
	}

	@Component
    class RestApi extends RouteBuilder {

        @Override
        public void configure() {        	
            restConfiguration()
                .contextPath("/camel-rest")
                .bindingMode(RestBindingMode.json);

            rest("/dms").description("DMS Rest Endpoint")
                .post("/")
                    .route().routeId("dms-api")
                    .log("${body}")
                    .split().jsonpath("$.objects")
                    .log("${body}")
                    .to("amqp:queue:threat_detected?exchangePattern=InOnly")
                    .endRest();
        }
    }
	
    /**
     * Main method to start the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}