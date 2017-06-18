package com.jbcn.social.wall.repository;

import com.jbcn.social.wall.model.Tweet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by miki on 17/06/17.
 */
@Repository("tweetRepository")
public interface TweetRepository extends JpaRepository<Tweet, String> {

    List<Tweet> findByDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable);

}
