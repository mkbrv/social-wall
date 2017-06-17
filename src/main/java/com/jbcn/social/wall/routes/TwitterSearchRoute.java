package com.jbcn.social.wall.routes;

import com.jbcn.social.wall.TwitterConfig;
import com.jbcn.social.wall.transformer.TweetTransformer;
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

        //separate call for each keyword
        String[] twitterKeywords = twitterConfig.getKeywords().split(",");
        for (int i = 0; i < twitterKeywords.length; i++) {
            twitterKeywords[i] = "twitter:search?numberOfPages=10&keywords=" + twitterKeywords[i];
        }

        from("direct:" + TWITTER_SEARCH)
                .multicast().to(twitterKeywords)
                .end()
                .to("log:DEBUG?showBody=true&showHeaders=true")
                .to("seda:twitter-search");

        from("seda:twitter-search")
                .split(body())
                .process(new TweetTransformer())
                .to(TweetsRoute.SEDA_ROUTE);
    }
}
