package com.jbcn.social.wall.web;

import com.jbcn.social.wall.model.Tweet;
import com.jbcn.social.wall.repository.TweetRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by miki on 17/06/17.
 */
@RestController
@RequestMapping("/api/tweets")
public class TweetAPI {

    @Resource
    private TweetRepository tweetRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Tweet>> findAll() {
        return new ResponseEntity<>(
                tweetRepository.findAllByOrderByCreatedAtDesc(
                        new PageRequest(1, 50)
                ), HttpStatus.OK);
    }


}
