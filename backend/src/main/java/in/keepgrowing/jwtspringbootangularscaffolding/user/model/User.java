package in.keepgrowing.jwtspringbootangularscaffolding.user.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UserCredentials userCredentials;

    protected User() {
    }

    public User(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    public Long getId() {
        return id;
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(userCredentials, user.userCredentials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userCredentials);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userCredentials=" + userCredentials +
                '}';
    }
}
