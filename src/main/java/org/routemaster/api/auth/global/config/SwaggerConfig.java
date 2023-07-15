package org.routemaster.api.auth.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Route Master Authorization API 명세서",
                version = "v1",
                contact = @Contact(
                        name = "엄태욱",
                        email = "taeukum@gmail.com"
                )
        )
)
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
}