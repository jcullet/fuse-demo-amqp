package com.redhat.fuse.boosters.rest.http;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A simple Camel REST DSL route that implement the greetings service.
 * 
 */
@Component
public class CamelRouter extends RouteBuilder {


    @Override
    public void configure() throws Exception {

        // @formatter:off
        //from("amqp:queue:incoming")
        //	.description("Listens on queue 'incoming' via AMQP 1.0 protocol")
        //	.routeId("amqp_receive_and_forward")
        //	.log("${body}");
        // @formatter:on
    }

}