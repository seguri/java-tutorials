package name.seguri.java.tutorials.springconditionals;

import java.util.StringJoiner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValueBean {
  private final String example;

  public ValueBean(@Value("${example:undefined}") String example) {
    this.example = example;
  }

  private String emoji() {
    return "undefined".equals(example) ? "❌" : "✅";
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ValueBean.class.getSimpleName() + "[", "]")
        .add("example='" + example + "' " + emoji())
        .toString();
  }
}
