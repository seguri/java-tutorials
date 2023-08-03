package name.seguri.java.springbuilderbean;

import java.util.Objects;
import java.util.StringJoiner;

public class User {
  private String name;
  private String email;

  private User(final Builder builder) {
    setName(builder.name);
    setEmail(builder.email);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (!(o instanceof final User that)) return false;
    return Objects.equals(name, that.name) && Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, email);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("email='" + email + "'")
        .toString();
  }

  public static final class Builder {
    private String name;
    private String email;

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
