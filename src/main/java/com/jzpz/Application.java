package com.jzpz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Administrator on 2016/9/21.
 */
@SpringBootApplication()
@EnableJpaRepositories(basePackages="com.jzpz.repository.**")
public class Application {
    public static void main(String [] args){
        SpringApplication.run(Application.class, args);
    }
}
