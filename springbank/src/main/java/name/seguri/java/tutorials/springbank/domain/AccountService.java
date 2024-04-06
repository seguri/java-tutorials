package name.seguri.java.tutorials.springbank.domain;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {
  private final AccountRepository accountRepository;

  @Transactional(readOnly = true)
  public List<UUID> findAllIds() {
    return accountRepository.findAllIds();
  }

  @Transactional
  public Account createAccount() {
    return accountRepository.save(Account.builder().build());
  }

  @Transactional
  public Account createAccount(final Account account) {
    return accountRepository.save(account);
  }
}
