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

  private final UserService userService;

  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public Iterable<User> getUsers() {
    return userService.getUsers();
  }

  @PostMapping("/users")
  public User createUser(
      @RequestBody final User dto,
      @RequestParam(name = "dbfail", required = false) Boolean dbfail) {
    return userService.createUser(dto, dbfail);
  }

  @PutMapping("/users/{id}")
  public User updateUser(
      @PathVariable final UUID id,
      @RequestBody final User dto,
      @RequestParam(name = "dbfail", required = false) Boolean dbfail) {
    return userService.updateUser(id, dto, dbfail);
  }
}
