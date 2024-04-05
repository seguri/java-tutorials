package name.seguri.java.tutorials.springbank;

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
}
