package name.seguri.java.springsyncdbkafkatx;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
  @Id private final UUID id;
  private final String name;
  private final String email;
  @Version private final Long version;

  // JPA
  protected User() {
    id = null;
    name = null;
    email = null;
    version = null;
  }

  private User(final Builder builder) {
    id = builder.id;
    name = builder.name;
    email = builder.email;
    version = builder.version;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Builder builder(final User copy) {
    Builder builder = new Builder();
    builder.id = copy.getId();
    builder.name = copy.getName();
    builder.email = copy.getEmail();
    builder.version = copy.getVersion();
    return builder;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public Long getVersion() {
    return version;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (!(o instanceof final User that)) return false;
    return Objects.equals(id, that.id)
        && Objects.equals(name, that.name)
        && Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, email);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("name='" + name + "'")
        .add("email='" + email + "'")
        .toString();
  }

  public static final class Builder {
    private UUID id = UUID.randomUUID();
    private String name;
    private String email;
    private Long version;

    private Builder() {}

    public Builder withName(final String val) {
      name = val;
      return this;
    }

    public Builder withEmail(final String val) {
      email = val;
      return this;
    }

    public User build() {
      return new User(this);
    }
  }
}
