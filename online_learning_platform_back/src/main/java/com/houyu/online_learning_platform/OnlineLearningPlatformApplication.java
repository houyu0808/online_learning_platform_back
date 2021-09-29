package com.houyu.online_learning_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@EnableJpaAuditing
@EnableJpaRepositories
@EntityScan
@ServletComponentScan
@SpringBootApplication
public class OnlineLearningPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineLearningPlatformApplication.class, args);
    }

}
