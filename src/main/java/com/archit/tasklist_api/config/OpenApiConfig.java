package com.archit.tasklist_api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                    name = "Archit Joshi",
                    url = "localhost:8080/swagger-ui/index.html",
                    email = "https://github.com/JoshiArchit"
                ),
                title = "Task List Spring Boot REST Api"
        )
)
public class OpenApiConfig {

}
