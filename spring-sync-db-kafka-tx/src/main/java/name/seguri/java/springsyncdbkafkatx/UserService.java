package name.seguri.java.springsyncdbkafkatx;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
  public List<User> createUsers(final List<User> dtos, final Long when) {
    return IntStream.range(0, dtos.size())
        .mapToObj(i -> createUser(dtos.get(i), when != null && when == i))
        .toList();
  }

  private void dbfail(final Boolean dbfail) {
    if (Boolean.TRUE.equals(dbfail)) {
      userRepository.generateException("Manually triggered exception");
    }
  }
}
