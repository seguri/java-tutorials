package name.seguri.java.springbuilderbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

  @Bean
  public User.Builder user() {
    return User.newBuilder().withName("John Doe").withEmail("doe@example.com");
  }
}
