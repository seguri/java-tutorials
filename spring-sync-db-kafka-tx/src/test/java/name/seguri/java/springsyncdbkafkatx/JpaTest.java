package name.seguri.java.springsyncdbkafkatx;

import static name.seguri.java.springsyncdbkafkatx.UserMother.john;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class JpaTest extends AbstractTest {

  @Test
  void createUser() {
    givenJohn();

    assertThat(userRepository.findAll()).containsExactly(john());
  }
}
