package uk.codersparks.homeassistant.denonservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DenonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DenonServiceApplication.class, args);
    }

}
