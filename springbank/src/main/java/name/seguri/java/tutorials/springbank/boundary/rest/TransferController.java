package name.seguri.java.tutorials.springbank.boundary.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import name.seguri.java.tutorials.springbank.boundary.rest.dto.TransferRequest;
import name.seguri.java.tutorials.springbank.domain.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransferController {

  private final TransferService transferService;

  @PostMapping("/transfers")
  public ResponseEntity<Void> transfer(@Valid @RequestBody final TransferRequest request) {
    transferService.transfer(request.from(), request.to(), request.amount());
    return ResponseEntity.noContent().build();
  }
}
