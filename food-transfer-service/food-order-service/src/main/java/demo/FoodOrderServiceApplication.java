package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableCircuitBreaker
public class FoodOrderServiceApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(FoodOrderServiceApplication.class, args);
    }

}
