package com.example.carthief.security;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String name;
    private String password;
    private Role role;

    public UserDto() {
    }

    public UserDto(Long id, String name, String password, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto userDto)) return false;
        return Objects.equals(id, userDto.id) && Objects.equals(name, userDto.name) && Objects.equals(password, userDto.password) && role == userDto.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, role);
    }
}
