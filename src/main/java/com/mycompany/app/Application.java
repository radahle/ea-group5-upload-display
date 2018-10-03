package com.mycompany.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        // <<<<<Testing>>>>>>
        DetermineExtention extention = new DetermineExtention();
        extention.displayData("test.js");
        extention.displayData("test.cs");
        extention.displayData("test.py");
        extention.displayData("test.png");
        extention.displayData("test.jpeg");
        extention.displayData("test.html");
        extention.displayData("test.pdf");
    }

}
