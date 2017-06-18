package com.jbcn.social.wall.routes;

import com.jbcn.social.wall.model.Tweet;
import com.jbcn.social.wall.repository.TweetRepository;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by miki on 17/06/17.
 */
@Component
public class TweetsRoute extends RouteBuilder {

    public static final String SEDA_ROUTE = "seda:tweets";

    @Resource
    TweetRepository tweetRepository;

    @Override
    public void configure() throws Exception {
        from(SEDA_ROUTE)
                .onException(Exception.class)
                .to("log:saveFailed")
                .end()
                .filter(exchange -> {
                    Tweet tweet = (Tweet) exchange.getIn().getBody();
                    Tweet previous = tweetRepository.findOne(tweet.getId());
                    if (previous != null && previous.getDeleted()) {
                        return false; //if it was deleted before we don't update
                    }
                    return true;
                })
                .to("bean:tweetRepository?method=save");
    }
}
