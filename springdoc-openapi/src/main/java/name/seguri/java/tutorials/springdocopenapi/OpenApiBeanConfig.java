package name.seguri.java.tutorials.springdocopenapi;

import static name.seguri.java.tutorials.springdocopenapi.Constants.API_DESCRIPTION;
import static name.seguri.java.tutorials.springdocopenapi.Constants.API_VERSION;
import static name.seguri.java.tutorials.springdocopenapi.Constants.BEARER_FORMAT;
import static name.seguri.java.tutorials.springdocopenapi.Constants.CONTACT_NAME;
import static name.seguri.java.tutorials.springdocopenapi.Constants.CONTACT_URL;
import static name.seguri.java.tutorials.springdocopenapi.Constants.LICENSE_NAME;
import static name.seguri.java.tutorials.springdocopenapi.Constants.LICENSE_URL;
import static name.seguri.java.tutorials.springdocopenapi.Constants.SECURITY_SCHEME_NAME;
import static name.seguri.java.tutorials.springdocopenapi.Constants.SECURITY_SCHEME_SCHEME;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "springdoc.config-style", havingValue = "beans")
public class OpenApiBeanConfig {
  private static final String API_TITLE = "Open API Configuration (with beans)";

  @Bean
  public OpenAPI customOpenAPI(@Value("${swagger.server-base-path}") String serverUrl) {
    return new OpenAPI()
        .components(new Components())
        .info(
            new Info()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version(API_VERSION)
                .license(new License().name(LICENSE_NAME).url(LICENSE_URL))
                .contact(new Contact().name(CONTACT_NAME).url(CONTACT_URL)))
        .addServersItem(new Server().url(serverUrl))
        .schemaRequirement(
            SECURITY_SCHEME_NAME,
            new SecurityScheme()
                .bearerFormat(BEARER_FORMAT)
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat(BEARER_FORMAT)
                .scheme(SECURITY_SCHEME_SCHEME))
        .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));
  }
}
