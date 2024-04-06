package name.seguri.java.tutorials.springbank.domain;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
  @Query("select a.id from Account a")
  List<UUID> findAllIds();
}
