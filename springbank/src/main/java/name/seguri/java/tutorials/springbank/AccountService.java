package name.seguri.java.tutorials.springbank;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
  private final AccountRepository accountRepository;

  @Transactional
  public Account createAccount() {
    return accountRepository.save(Account.builder().build());
  }
}
