package name.seguri.java.tutorials.springbank.boundary.rest.dto;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TransferRequestTest {
  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @ParameterizedTest
  @ValueSource(ints = {-1, 0})
  void validate_badAmount_fail(final int amount) {
    final var sut =
        new TransferRequest(UUID.randomUUID(), UUID.randomUUID(), BigDecimal.valueOf(amount));

    final var result = validator.validate(sut);

    assertThat(result).hasSize(1);
    final var violation = result.iterator().next();
    assertThat(violation.getPropertyPath().toString()).isEqualTo("amount");
  }
}
