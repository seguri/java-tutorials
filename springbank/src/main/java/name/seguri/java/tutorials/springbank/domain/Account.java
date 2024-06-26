package name.seguri.java.tutorials.springbank.domain;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "springbank__accounts")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

  @Id @Builder.Default private UUID id = Generators.timeBasedEpochGenerator().generate();

  @Builder.Default private BigDecimal balance = BigDecimal.ZERO;

  @Version private Integer version;

  public void deposit(final BigDecimal amount) {
    balance = balance.add(amount);
  }

  public void withdraw(final BigDecimal amount) {
    if (balance.compareTo(amount) < 0) {
      // TODO Replace with JSON Problem
      throw new IllegalArgumentException("Insufficient funds");
    }
    balance = balance.subtract(amount);
  }
}
