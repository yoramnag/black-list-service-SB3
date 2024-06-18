package com.example.blackListService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Black list microservice REST API Documentation",
				description = "Black list REST API for credit card microservice project",
				version = "v1",
				contact = @Contact(
						name = "Yoram Nagawker",
						email = "yoramnag@gmail.com"
				),
				license = @License(
						name = "Apache 2.0"
				)
		)
)
public class BlackListServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackListServiceApplication.class, args);
	}

}
