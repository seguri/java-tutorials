package name.seguri.java.tutorials.springbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class Application {

  public static void main(final String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
