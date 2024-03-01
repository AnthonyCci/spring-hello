package com.cci.demohello;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoHelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoHelloApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Demo Hello Spring Boot API")
                .version("1.0.0")
                .description("Sample app API in Spring Boot")
                .termsOfService("https://swagger.io/terms/")
                .license(
                        new License().name("Apache 2.0").url("https://springdoc.org"))
        );
    }
}
