package com.reymitech.app.credits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroserviceCreditsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceCreditsApplication.class, args);
    }

}
