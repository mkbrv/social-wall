package com.jbcn.social.wall;

import com.jbcn.social.wall.model.MediaEntity;
import com.jbcn.social.wall.model.Tweet;
import com.jbcn.social.wall.repository.TweetRepository;
import com.jbcn.social.wall.routes.TweetsRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by miki on 17/06/17.
 */
public class TweetCanBeSaved extends SocialWallApplicationTests {

    @Resource
    CamelContext camelContext;

    @Resource
    TweetRepository tweetRepository;

    @Test
    public void canBeSaved() {
        final String id = UUID.randomUUID().toString();
        final String photoId = UUID.randomUUID().toString();
        ProducerTemplate template = camelContext.createProducerTemplate();
        template.sendBody(TweetsRoute.SEDA_ROUTE, ExchangePattern.InOut,
                new Tweet().setId(id)
                        .addPhoto(new MediaEntity()
                                .setId(photoId))
        );

        Tweet tweet = tweetRepository.findOne(id);
        assertNotNull(tweet);
        assertEquals(photoId, tweet.getPhotos().get(0).getId());
    }


}
