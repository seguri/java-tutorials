package name.seguri.java.tutorials.springbank.domain;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferService {
  private final AccountRepository accountRepository;

  @Transactional
  public void transfer(final UUID from, final UUID to, final BigDecimal amount) {
    accountRepository.findById(from).orElseThrow().withdraw(amount);
    accountRepository.findById(to).orElseThrow().deposit(amount);
  }
}
