package name.seguri.java.springsyncdbkafkatx;

import java.util.UUID;
import org.springframework.test.util.ReflectionTestUtils;

public class UserMother {
  public static User john() {
    final var john = User.builder().withName("John Doe").withEmail("john.doe@example.com").build();
    setId(john, "04A1EDB8-7612-40FA-93F7-15E6F48A8419");
    return john;
  }

  private static void setId(final User user, final String uuid) {
    ReflectionTestUtils.setField(user, "id", UUID.fromString(uuid));
  }
}
