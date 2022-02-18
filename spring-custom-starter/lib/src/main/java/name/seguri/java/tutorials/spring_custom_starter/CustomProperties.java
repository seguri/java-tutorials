package name.seguri.java.tutorials.spring_custom_starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom")
public class CustomProperties {

  private String foo;
  private String bar;

  public String getFoo() {
    return foo;
  }

  public void setFoo(String foo) {
    this.foo = foo;
  }

  public String getBar() {
    return bar;
  }

  public void setBar(String bar) {
    this.bar = bar;
  }
}
