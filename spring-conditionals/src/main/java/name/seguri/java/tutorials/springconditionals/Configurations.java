package name.seguri.java.tutorials.springconditionals;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations {

  @Bean
  @ConditionalOnMissingBean(PropertyBean.class)
  public PropertyBean propertyBean() {
    return new PropertyBean("ConditionalOnProperty ❌");
  }
}
