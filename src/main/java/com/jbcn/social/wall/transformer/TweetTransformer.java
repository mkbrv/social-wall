package com.jbcn.social.wall.transformer;

import com.jbcn.social.wall.model.Tweet;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import twitter4j.Status;

import java.util.Arrays;

import static java.util.Optional.ofNullable;

/**
 * Created by miki on 17/06/17.
 */
public class TweetTransformer implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        final Message message = exchange.getIn();
        if (message.getBody() instanceof Status) {
            final Tweet tweet = fromStatus((Status) message.getBody());
            message.setBody(tweet);
            message.setHeader("retweet", tweet.getRetweeted() != null);
            message.setHeader("tweet_id", tweet.getId());
        }
    }

    /**
     * Transforms status to a tweet;
     *
     * @param status
     * @return
     */
    Tweet fromStatus(final Status status) {
        Tweet tweet = new Tweet()
                .setId(String.valueOf(status.getId()))
                .setCreatedAt(status.getCreatedAt())
                .setFavoriteCount(status.getFavoriteCount())
                .setRetweetCount(status.getRetweetCount())
                .setText(sanitizeUTF8MB4(status.getText()))
                .setProfileImage(status.getUser().getProfileImageURL())
                .setUserHandle(status.getUser().getScreenName())
                .setUserName(sanitizeUTF8MB4(status.getUser().getName()))
                .setUserLocation(status.getUser().getLocation());

        ofNullable(status.getRetweetedStatus()).ifPresent(retweeted -> tweet.setRetweeted(fromStatus(retweeted)));

        ofNullable(status.getMediaEntities())
                .ifPresent(mediaEntities -> Arrays.stream(status.getMediaEntities())
                        .filter(mediaEntity -> mediaEntity.getType().equalsIgnoreCase("photo"))
                        .forEach(mediaEntity -> tweet.addPhoto(
                                new com.jbcn.social.wall.model.MediaEntity()
                                        .setId(String.valueOf(mediaEntity.getId()))
                                        .setTweet(tweet)
                                        .setUrl(mediaEntity.getMediaURL())))
                );
        return tweet;
    }

    private String sanitizeUTF8MB4(final String toSanitize) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < toSanitize.length(); i++) {
            if (!Character.isSurrogate(toSanitize.charAt(i))) {
                result.append(toSanitize.charAt(i));
            }
        }

        return result.toString();
    }

}
