package com.mthree.guessthenumber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@ComponentScan("com.mthree.guessthenumber.serviceLayer")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
