package name.seguri.java.tutorials.springbank.boundary.rest.dto;

import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record CreateAccountRequest(@Positive BigDecimal balance) {}
