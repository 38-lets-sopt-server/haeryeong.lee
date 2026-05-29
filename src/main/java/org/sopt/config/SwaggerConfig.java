package org.sopt.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    String securitySchemeName = "JWT";

    return new OpenAPI()
        .components(new Components()
                .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                    .name(securitySchemeName)
                    .type(Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                )
        )
        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
        .servers(List.of(
            new Server().url("/")
        ));
  }
}
