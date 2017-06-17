package com.jbcn.social.wall.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.idempotent.MemoryIdempotentRepository;
import org.springframework.stereotype.Component;

/**
 * Created by miki on 17/06/17.
 */
@Component
public class TweetsRoute extends RouteBuilder {

    public static final String SEDA_ROUTE = "seda:tweets";

    @Override
    public void configure() throws Exception {
        from(SEDA_ROUTE)
                .onException(Exception.class)
                .to("log:saveFailed")
                .end()
                .idempotentConsumer(header("tweet_id"))
                .messageIdRepository(new MemoryIdempotentRepository()).skipDuplicate(false)
                .filter(exchangeProperty(Exchange.DUPLICATE_MESSAGE).isEqualTo(true))
                .to("log:duplicate")
                .stop()
                .end()
                .to("bean:tweetRepository?method=save");
    }
}
