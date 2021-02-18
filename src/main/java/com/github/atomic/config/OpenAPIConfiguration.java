package com.github.atomic.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*@OpenAPIDefinition(
        info = @Info(
                title = "Foodify API Documentation",
                description = " ",
                contact = @Contact(
                        name = "Alex",
                        url = "https://reflectoring.io",
                        email = "petros.stergioulas94@gmail.com"
                ),
                license = @License(
                        name = "MIT Licence",
                        url = "https://github.com/thombergs/code-examples/blob/master/LICENSE")
        ),
        servers = @Server(url = "http://localhost:8080")
)*/

/*@OpenAPIDefinition(
        info = @Info(
                title = "Foodify API Documentation",
                description = " ",
                contact = @Contact(
                        name = "Alex"
                )
        )
)*/
@Configuration
public class OpenAPIConfiguration {

    public static final String securitySchemeName = "bearerAuth";

    @Autowired
    EnvironmentConfig environmentConfig;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
//                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))
                )
                .info(new Info()
                                .title(environmentConfig.getAppName()+" API Documentation")
                                .description(
                                        "This is the documentation for "+environmentConfig.getAppName()+" RESTful service.</br>" +
                                                "<a target=\"_blank\" href=\"https://github.com/jirutka/rsql-parser#grammar-and-semantic\">rsql-parser for search api</a>"
                                )
                        /*.contact(new Contact()
                                .name("Alex")
                                .url("http://ornach.com/")
                                .email("alex31n@yahoo.com")
                        )*/
                )
                /*.externalDocs(new ExternalDocumentation()
                        .description("rsql-parser for search api")
                        .url("https://github.com/jirutka/rsql-parser#grammar-and-semantic")
                )*/;
    }
}
