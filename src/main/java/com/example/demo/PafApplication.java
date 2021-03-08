package com.example.demo;



import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication

public class PafApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        new PafApplication().configure(new SpringApplicationBuilder(PafApplication.class)).run(args);
    }

}
