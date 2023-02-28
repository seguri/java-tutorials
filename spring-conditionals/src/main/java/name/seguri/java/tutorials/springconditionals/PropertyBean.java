package name.seguri.java.tutorials.springconditionals;

import java.util.StringJoiner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@ConditionalOnProperty(name = "example", havingValue = "class")
public class PropertyBean {
  private final String string;

  public PropertyBean() {
    this("ConditionalOnProperty ✅");
  }

  public PropertyBean(String string) {
    this.string = string;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", PropertyBean.class.getSimpleName() + "[", "]")
        .add("string='" + string + "'")
        .toString();
  }
}
