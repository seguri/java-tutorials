package name.seguri.java.springsyncdbkafkatx;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserRepository userRepository;

  public UserController(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/users")
  public Iterable<User> getUsers() {
    return userRepository.findAll();
  }

  @PostMapping("/users")
  public User createUser(@RequestBody final User dto) {
    final var next = User.builder().withName(dto.getName()).withEmail(dto.getEmail()).build();
    return userRepository.save(next);
  }

  @PutMapping("/users/{id}")
  public User updateUser(@PathVariable final UUID id, @RequestBody final User dto) {
    final var curr = userRepository.findById(id).orElseThrow();
    final var next = User.builder(curr).withName(dto.getName()).withEmail(dto.getEmail()).build();
    return userRepository.save(next);
  }
}
