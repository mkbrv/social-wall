package com.jbcn.social.wall;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * Created by miki on 17/06/17.
 */
@Component
@ConfigurationProperties("twitter")
public class TwitterConfig {


    private String accessToken;
    private String accessTokenSecret;
    private String consumerKey;
    private String consumerSecret;
    private String keywords;

    public String getAccessToken() {
        return accessToken;
    }

    public TwitterConfig setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }

    public TwitterConfig setAccessTokenSecret(String accessTokenSecret) {
        this.accessTokenSecret = accessTokenSecret;
        return this;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public TwitterConfig setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
        return this;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public TwitterConfig setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
        return this;
    }

    public TwitterConfig setKeywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public String getKeywords() {
        return keywords;
    }
}
