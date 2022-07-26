package com.github.americanoicetea.java.springmvcdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application stater
 *
 */
@SpringBootApplication(scanBasePackages = "com.github.americanoicetea.java")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
