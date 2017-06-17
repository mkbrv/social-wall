package com.jbcn.social.wall;

import org.apache.camel.CamelContext;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;

import static com.jbcn.social.wall.routes.TwitterSearchRoute.TWITTER_SEARCH;
import static org.junit.Assert.assertNotNull;

/**
 * Created by miki on 17/06/17.
 */
public class TweetCanBeSearched extends SocialWallApplicationTests {

    @Resource
    CamelContext camelContext;

    @Test
    @Ignore
    public void canSearchTweets() {
        ProducerTemplate template = camelContext.createProducerTemplate();
        Object reply = template.sendBody("direct:" + TWITTER_SEARCH, ExchangePattern.InOut, null);
        assertNotNull(reply);
    }


}
