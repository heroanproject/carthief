package com.example.carthief.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name = "Dealer.cars", attributeNodes = @NamedAttributeNode("cars"))
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    String name;

    @ManyToMany
    private Set<Car> cars = new HashSet<>();

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dealer dealer = (Dealer) o;
        return Objects.equals(id, dealer.id) && Objects.equals(name, dealer.name) && Objects.equals(cars, dealer.cars);
    }

    @Override
    public int hashCode () {
        return Objects.hash(id, name, cars);
    }
}
