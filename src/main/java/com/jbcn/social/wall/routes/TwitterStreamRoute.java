package com.jbcn.social.wall.routes;

import com.jbcn.social.wall.TwitterConfig;
import com.jbcn.social.wall.transformer.TweetTransformer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.twitter.TwitterComponent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by miki on 17/06/17.
 */
@Component
public class TwitterStreamRoute extends RouteBuilder {

    @Resource
    TwitterConfig twitterConfiguration;


    private void initConfiguration() {
        TwitterComponent twitterComponent = getContext().getComponent("twitter", TwitterComponent.class);
        twitterComponent.setAccessToken(twitterConfiguration.getAccessToken());
        twitterComponent.setAccessTokenSecret(twitterConfiguration.getAccessTokenSecret());
        twitterComponent.setConsumerKey(twitterConfiguration.getConsumerKey());
        twitterComponent.setConsumerSecret(twitterConfiguration.getConsumerSecret());
    }


    @Override
    public void configure() throws Exception {
        initConfiguration();
        /*from("twitter:streaming/filter?keywords=" + twitterConfiguration.getKeywords())
                .process(new TweetTransformer())
                .log("log:received $simple{in.body}")
                .to(TweetsRoute.SEDA_ROUTE);*/
    }
}
