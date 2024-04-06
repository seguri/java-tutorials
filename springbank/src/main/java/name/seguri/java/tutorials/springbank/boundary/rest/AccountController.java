package name.seguri.java.tutorials.springbank.boundary.rest;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import name.seguri.java.tutorials.springbank.boundary.rest.dto.CreateAccountRequest;
import name.seguri.java.tutorials.springbank.domain.Account;
import name.seguri.java.tutorials.springbank.domain.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @PostMapping("/accounts")
  public ResponseEntity<Object> createAccount(@Valid @RequestBody final CreateAccountRequest req) {
    final var dbAccount =
        accountService.createAccount(Account.builder().balance(req.balance()).build());

    final URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(dbAccount.getId())
            .toUri();

    return ResponseEntity.created(location).body(dbAccount);
  }
}
