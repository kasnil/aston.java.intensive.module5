package aston.java.intensive.module5.domain;

import aston.java.intensive.module5.domain.dto.Email;
import aston.java.intensive.module5.domain.dto.Password;
import aston.java.intensive.module5.domain.dto.UserName;
import aston.java.intensive.module5.utils.guard.Ensure;

import java.util.Objects;

public final class User implements Comparable<User> {
    private final String name;
    private final String email;
    private final String password;

    private User(
            UserName name,
            Email email,
            Password password
    ) {
        this.name = name.value();
        this.email = email.value();
        this.password = password.value();
    }

    public String getName() { return name; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }

    public static class Builder {
        private UserName name;
        private Email email;
        private Password password;

        public Builder setName(String name) {
            return setName(UserName.of(name).orElseThrow());
        }

        public Builder setName(UserName name) {
            this.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            return setPassword(Password.of(password).orElseThrow());
        }

        public Builder setPassword(Password password) {
            this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            return setEmail(Email.of(email).orElseThrow());
        }

        public Builder setEmail(Email email) {
            this.email = email;
            return this;
        }

        public User build() {
            Ensure.that(name).isNotNull();
            Ensure.that(email).isNotNull();
            Ensure.that(password).isNotNull();
            return new User(name, email, password);
        }
    }

    @Override
    public int compareTo(User other) {
        int nameCompare = this.name.compareTo(other.name);
        if (nameCompare != 0) return nameCompare;
        
        int emailCompare = this.email.compareTo(other.email);
        if (emailCompare != 0) return emailCompare;
        
        return this.password.compareTo(other.password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
               Objects.equals(password, user.password) &&
               Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, email);
    }

    @Override
    public String toString() {
        return String.format("User{name='%s', email='%s'}", name, email);
    }
}
