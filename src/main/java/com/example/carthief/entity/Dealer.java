package com.example.carthief.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NamedEntityGraph(name = "Dealer.cars", attributeNodes = @NamedAttributeNode("cars"))
public class Dealer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private Set<Car> cars = new HashSet<>();
}
