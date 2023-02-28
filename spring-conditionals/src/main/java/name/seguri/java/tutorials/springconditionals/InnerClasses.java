package name.seguri.java.tutorials.springconditionals;

import java.util.StringJoiner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class InnerClasses {
  private final String string = "InnerClass";
  private final Emoji emoji;

  public InnerClasses(Emoji emoji) {
    this.emoji = emoji;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", InnerClasses.class.getSimpleName() + "[", "]")
        .add("string='" + string + "' " + emoji)
        .toString();
  }

  @Bean
  @ConditionalOnProperty(value = "${example}", havingValue = "innerclass")
  Emoji availableEmoji() {
    return () -> "✅";
  }

  @Bean
  @ConditionalOnMissingBean(Emoji.class)
  Emoji missingEmoji() {
    return () -> "❌";
  }

  @FunctionalInterface
  interface Emoji {
    String getEmoji();
  }
}
