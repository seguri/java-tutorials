package name.seguri.java.tutorials.springbank.boundary.rest.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record TransferRequest(
    @NotNull UUID from,
    @NotNull UUID to,
    @DecimalMin(value = "0.01") @Digits(integer = 4, fraction = 2) BigDecimal amount) {}
