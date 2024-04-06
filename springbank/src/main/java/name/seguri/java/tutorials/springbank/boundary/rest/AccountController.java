package name.seguri.java.tutorials.springbank.boundary.rest;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.val;
import name.seguri.java.tutorials.springbank.boundary.rest.dto.CreateAccountRequest;
import name.seguri.java.tutorials.springbank.boundary.rest.dto.CreateAccountResponse;
import name.seguri.java.tutorials.springbank.domain.Account;
import name.seguri.java.tutorials.springbank.domain.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @GetMapping("/accounts")
  public List<UUID> findAllIds() {
    return accountService.findAllIds();
  }

  @PostMapping("/accounts")
  public ResponseEntity<Object> createAccount(@Valid @RequestBody final CreateAccountRequest req) {
    val dbAccount = accountService.createAccount(Account.builder().balance(req.balance()).build());

    val location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(dbAccount.getId())
            .toUri();

    val responseBody =
        CreateAccountResponse.builder()
            .id(dbAccount.getId())
            .balance(dbAccount.getBalance())
            .build();

    return ResponseEntity.created(location).body(responseBody);
  }
}
