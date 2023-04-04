package com.example.carthief.security;

import com.mongodb.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Getter
@Setter
@Document(collection = "users")
public class UserCredentials {

    @Id
    private String id;

    @NonNull
    @Column(unique = true)
    private String name;

    @NonNull
    private String password;

    @NonNull
    private Role role;

    public UserCredentials(@NonNull String name, @NonNull String password, @NonNull Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCredentials user)) return false;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(password, user.password) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, role);
    }
}
