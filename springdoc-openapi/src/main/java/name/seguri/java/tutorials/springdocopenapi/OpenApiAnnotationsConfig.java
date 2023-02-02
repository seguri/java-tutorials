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

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "springdoc.config-style", havingValue = "annotations")
@OpenAPIDefinition(
    info =
        @Info(
            title = "Open API Configuration (with annotations)",
            description = API_DESCRIPTION,
            version = API_VERSION,
            license = @License(name = LICENSE_NAME, url = LICENSE_URL),
            contact = @Contact(name = CONTACT_NAME, url = CONTACT_URL)),
    servers = @Server(url = "${swagger.server-base-path}"),
    security = {@SecurityRequirement(name = SECURITY_SCHEME_NAME)})
@SecurityScheme(
    name = SECURITY_SCHEME_NAME,
    type = SecuritySchemeType.HTTP,
    scheme = SECURITY_SCHEME_SCHEME,
    bearerFormat = BEARER_FORMAT)
public class OpenApiAnnotationsConfig {}
