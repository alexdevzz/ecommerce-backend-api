package com.alexdev.ecommercebackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(
                version = "${open_api.version_api}",
                title = "${open_api.tittle}",
                description = "${open_api.description}",
                contact = @Contact(
                        name = "${open_api.contact.name}"
                )
        ),
        servers = {
                @Server(url = "${open_api.servers.local.url}",
                        description = "${open_api.servers.local.description}"),
                @Server(url = "${open_api.servers.production.url}",
                        description = "${open_api.servers.production.description}")
        }
)
@Configuration
public class OpenApiConfig {

}

