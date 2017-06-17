package com.jbcn.social.wall.routes;

import com.jbcn.social.wall.TwitterConfig;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by miki on 17/06/17.
 */
@Component
public class TwitterSearchRoute extends RouteBuilder {


    public static final String TWITTER_SEARCH = "twitter-search";

    @Resource
    TwitterConfig twitterConfig;

    @Override
    public void configure() throws Exception {
        from("timer://" + TWITTER_SEARCH + "?period=5m")
                .to("direct:" + TWITTER_SEARCH);
        from("direct:" + TWITTER_SEARCH)
                .to("twitter:search?keywords=" + twitterConfig.getKeywords());
    }
}
