package com.jbcn.social.wall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication
@ComponentScan(basePackages = {"com.jbcn.social.wall"})
public class SocialWallApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SocialWallApplication.class, args);
    }

}
