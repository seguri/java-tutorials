package name.seguri.java.springsyncdbkafkatx;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserRepository userRepository;
  private final UserProducer userProducer;

  public UserController(final UserRepository userRepository, final UserProducer userProducer) {
    this.userRepository = userRepository;
    this.userProducer = userProducer;
  }

  @GetMapping("/users")
  public Iterable<User> getUsers() {
    return userRepository.findAll();
  }

  @PostMapping("/users")
  public User createUser(
      @RequestBody final User dto,
      @RequestParam(name = "dbfail", required = false) Boolean dbfail) {
    final var next = User.builder().withName(dto.getName()).withEmail(dto.getEmail()).build();
    final var saved = userRepository.save(next);
    if (Boolean.TRUE.equals(dbfail)) {
      userRepository.generateException("Manually triggered exception");
    }
    userProducer.sendUser(saved);
    return saved;
  }

  @PutMapping("/users/{id}")
  public User updateUser(
      @PathVariable final UUID id,
      @RequestBody final User dto,
      @RequestParam(name = "dbfail", required = false) Boolean dbfail) {
    final var curr = userRepository.findById(id).orElseThrow();
    final var next = User.builder(curr).withName(dto.getName()).withEmail(dto.getEmail()).build();
    final var saved = userRepository.save(next);
    if (Boolean.TRUE.equals(dbfail)) {
      userRepository.generateException("Manually triggered exception");
    }
    userProducer.sendUser(saved);
    return saved;
  }

  @PostMapping("/dbfail")
  public void dbfail() {
    userRepository.generateException("Manually triggered exception");
  }
}
