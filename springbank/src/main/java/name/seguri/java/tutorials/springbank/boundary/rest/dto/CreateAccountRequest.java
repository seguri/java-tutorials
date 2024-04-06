package name.seguri.java.tutorials.springbank.boundary.rest.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import java.math.BigDecimal;

public record CreateAccountRequest(
    @DecimalMin(value = "0.01") @Digits(integer = 4, fraction = 2) BigDecimal balance) {}
