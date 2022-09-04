package com.example.graphql;

import com.example.graphql.model.Car;
import com.example.graphql.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GraphqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlApplication.class, args);
    }



    @Bean
    CommandLineRunner runner ( CarRepository carService) {
        return args -> {
            carService.save(new Car("class A", "Skoda", (long) 140.15,
                    "123-qwe-11","Available"));
            carService.save(new Car("class A", "Skoda", (long) 140.15,
                    "123-qwe-22","Available"));
            carService.save(new Car("class A", "Skoda", (long) 150.15,
                    "123-qwe-12","Available"));
            carService.save(new Car("class B", "Fiat", (long) 50.50,
                    "111-qwe-11","Available"));
            carService.save(new Car("class A", "Fiat", (long) 100.50,
                    "111-qwe-12","Available"));
            carService.save(new Car("class A", "Kia", (long) 110.15,
                    "122-qwe-11","Available"));
            carService.save(new Car("class A", "Kia", (long) 120.60,
                    "122-qwe-22","Available"));
            carService.save(new Car("class C", "Kia", (long) 60.60,
                    "122-qwe-28","Available"));

        };
    }

}
