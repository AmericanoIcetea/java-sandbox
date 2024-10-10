package com.americanoicetea.java.bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.americanoicetea.java")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}