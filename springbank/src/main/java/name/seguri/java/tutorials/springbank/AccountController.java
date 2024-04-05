package name.seguri.java.tutorials.springbank;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @PostMapping("/accounts")
  public ResponseEntity<Object> createAccount() {
    final var dbAccount = accountService.createAccount();

    final URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(dbAccount.getId())
            .toUri();

    return ResponseEntity.created(location).body(dbAccount);
  }
}
