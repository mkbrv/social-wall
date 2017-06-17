package com.jbcn.social.wall.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by miki on 17/06/17.
 */
@Component
public class Timer extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        //from("timer:foo")
          //      .to("log:bar");
    }
}
