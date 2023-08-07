package name.seguri.java.springsyncdbkafkatx;

import jakarta.transaction.Transactional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserProducer userProducer;

  public UserService(final UserRepository userRepository, final UserProducer userProducer) {
    this.userRepository = userRepository;
    this.userProducer = userProducer;
  }

  public Iterable<User> getUsers() {
    return userRepository.findAll();
  }

  @Transactional
  public User createUser(final User dto, final Boolean dbfail) {
    final var next = User.builder().withName(dto.getName()).withEmail(dto.getEmail()).build();
    final var saved = userRepository.save(next);
    dbfail(dbfail);
    userProducer.sendUser(saved);
    return saved;
  }

  @Transactional
  public User updateUser(final UUID id, final User dto, final Boolean dbfail) {
    final var curr = userRepository.findById(id).orElseThrow();
    final var next = User.builder(curr).withName(dto.getName()).withEmail(dto.getEmail()).build();
    final var saved = userRepository.save(next);
    dbfail(dbfail);
    userProducer.sendUser(saved);
    return saved;
  }

  public void dbfail(final Boolean dbfail) {
    if (Boolean.TRUE.equals(dbfail)) {
      userRepository.generateException("Manually triggered exception");
    }
  }
}
