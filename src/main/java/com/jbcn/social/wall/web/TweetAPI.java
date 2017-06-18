package com.jbcn.social.wall.web;

import com.jbcn.social.wall.model.Tweet;
import com.jbcn.social.wall.repository.TweetRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by miki on 17/06/17.
 */
@RestController
@RequestMapping("/api/tweets")
@CrossOrigin
public class TweetAPI {

    @Resource
    private TweetRepository tweetRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Tweet>> findAll() {
        final List<Tweet> latest20Tweets = tweetRepository.findByDeletedIsFalseOrderByCreatedAtDesc(
                new PageRequest(0, 20));
        return new ResponseEntity<>(latest20Tweets, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Tweet> deleteTweet(@PathVariable("id") final String id) {
        Tweet tweet = tweetRepository.findOne(id);
        tweet.setDeleted(Boolean.TRUE);
        tweetRepository.save(tweet);
        return new ResponseEntity<Tweet>(tweet, HttpStatus.ACCEPTED);
    }


}
