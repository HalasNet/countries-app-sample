package com.github.noraui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.github.noraui.repository" })
@EntityScan(basePackages = { "com.github.noraui.model" })
@EnableTransactionManagement
public class MainApp {
    public static void main(String[] args) throws Exception {
        new SpringApplication(MainApp.class).run(args);
    }
}