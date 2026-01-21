package domain;

import java.util.Objects;

public class User implements Comparable<User> {
    private final String name;
    private final String password;
    private final String email;

    private User(Builder builder) {
        this.name = builder.name;
        this.password = builder.password;
        this.email = builder.email;
    }

    public String getName() { return name; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }

    public static class Builder {
        private String name;
        private String password;
        private String email;

        public Builder setName(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Имя не может быть пустым");
            }
            this.name = name.trim();
            return this;
        }

        public Builder setPassword(String password) {
            if (password == null || password.length() < 6) {
                throw new IllegalArgumentException("Пароль должен содержать минимум 6 символов");
            }
            this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                throw new IllegalArgumentException("Неверный формат email");
            }
            this.email = email.trim().toLowerCase();
            return this;
        }

        public User build() {
            if (name == null || password == null || email == null) {
                throw new IllegalStateException("Все поля должны быть установлены");
            }
            return new User(this);
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
