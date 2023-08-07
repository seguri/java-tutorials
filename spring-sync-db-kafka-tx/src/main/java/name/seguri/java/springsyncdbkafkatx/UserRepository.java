package name.seguri.java.springsyncdbkafkatx;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  @Query(nativeQuery = true, value = "SELECT generate_exception(:errorMessage)")
  void generateException(String errorMessage);
}
