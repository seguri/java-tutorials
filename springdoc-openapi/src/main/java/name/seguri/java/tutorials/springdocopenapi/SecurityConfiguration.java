package name.seguri.java.tutorials.springdocopenapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // FIXME How to configure this
        return http.authorizeRequests(this::permitAll).build();
    }

    private void permitAll(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
        registry.anyRequest().permitAll();
    }

    private void setup(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
//        registry.antMatchers("/index.html", "/swagger-ui/**").permitAll().anyRequest().authenticated();
    }

    private void authorizeRequestConfiguration(
            final ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
                    configurer) {
        configurer
                .antMatchers("/spec/**", "/swagger-ui.html", "/swagger-ui/**")
                .permitAll()
                .antMatchers("/actuator", "/actuator/**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
