package com.example.carthief.security;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UserDto {

    private String name;
    private String password;
    private Role role;

    public UserDto() {
    }

    public UserDto(UserCredentials user) {
        this.name = user.getName();
        this.password = user.getPassword();
        this.role = Role.valueOf(role.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto userDto)) return false;
        return Objects.equals(name, userDto.name) && Objects.equals(password, userDto.password) && role == userDto.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, role);
    }
}
