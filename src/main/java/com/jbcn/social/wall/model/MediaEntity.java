package com.jbcn.social.wall.model;

import javax.persistence.*;

/**
 * Created by miki on 17/06/17.
 */
@Entity
public class MediaEntity {

    @Id
    private String id;

    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    public String getId() {
        return id;
    }

    public MediaEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public MediaEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public MediaEntity setTweet(Tweet tweet) {
        this.tweet = tweet;
        return this;
    }
}
