package name.seguri.java.tutorials.springbank.domain;

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

  @Transactional
  public Account createAccount(final Account account) {
    return accountRepository.save(account);
  }
}
