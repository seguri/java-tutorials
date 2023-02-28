package name.seguri.java.tutorials.springconditionals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
  private static final Logger logger = LoggerFactory.getLogger(Application.class);

  private final ValueBean valueBean;
  private final PropertyBean propertyBean;
  private final InnerClasses innerClasses;

  public Application(ValueBean valueBean, PropertyBean propertyBean, InnerClasses innerClasses) {
    this.valueBean = valueBean;
    this.propertyBean = propertyBean;
    this.innerClasses = innerClasses;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) {
    logger.info("ValueBean: {}", valueBean);
    logger.info("ClassBean: {}", propertyBean);
    logger.info("InnerClasses: {}", innerClasses);
  }
}
