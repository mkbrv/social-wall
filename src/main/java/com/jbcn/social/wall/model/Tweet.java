package com.jbcn.social.wall.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by miki on 17/06/17.
 */
@Entity
public class Tweet {

    @Id
    private String id;
    private String text;
    private Date createdAt;
    private int favoriteCount;
    private int retweetCount;
    private String userHandle;
    private String userName;
    private String userLocation;
    private String profileImage;

    @Transient
    private Tweet retweeted;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tweet", cascade = CascadeType.ALL)
    private List<MediaEntity> photos = new ArrayList<>();

    public String getId() {
        return id;
    }

    public Tweet setId(String id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public Tweet setText(String text) {
        this.text = text;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Tweet setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public Tweet setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
        return this;
    }

    public String getUserHandle() {
        return userHandle;
    }

    public Tweet setUserHandle(String userHandle) {
        this.userHandle = userHandle;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Tweet setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public Tweet setUserLocation(String userLocation) {
        this.userLocation = userLocation;
        return this;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public Tweet setProfileImage(String profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    public Tweet setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
        return this;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public Tweet getRetweeted() {
        return retweeted;
    }

    public Tweet setRetweeted(Tweet retweeted) {
        this.retweeted = retweeted;
        return this;
    }

    public Tweet setPhotos(List<MediaEntity> photos) {
        this.photos = photos;
        return this;
    }

    public List<MediaEntity> getPhotos() {
        return photos;
    }

    public Tweet addPhoto(final MediaEntity photo) {
        this.photos.add(photo);
        photo.setTweet(this);
        return this;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", favoriteCount=" + favoriteCount +
                ", retweetCount=" + retweetCount +
                ", userHandle='" + userHandle + '\'' +
                ", userName='" + userName + '\'' +
                ", userLocation='" + userLocation + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", retweeted=" + retweeted +
                ", photos=" + photos +
                '}';
    }
}
