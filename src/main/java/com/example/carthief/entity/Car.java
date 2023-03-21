package com.example.carthief.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Car {
    public Car(Long id, String name, BigDecimal price) {
        this.id = id;
        this.price = price;
        this.name = name;

    }
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        public Long id;
        String name;
        BigDecimal price;

    public Car() {

    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

    }