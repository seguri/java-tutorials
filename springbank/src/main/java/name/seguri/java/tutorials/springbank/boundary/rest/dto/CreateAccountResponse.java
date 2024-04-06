package name.seguri.java.tutorials.springbank.boundary.rest.dto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

@Builder
public record CreateAccountResponse(UUID id, BigDecimal balance) {}
