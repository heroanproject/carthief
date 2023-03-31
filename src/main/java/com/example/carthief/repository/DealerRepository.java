package com.example.carthief.repository;

import com.example.carthief.entity.Dealer;
import com.example.carthief.projection.DealerName;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface DealerRepository extends ListCrudRepository<Dealer,Long> {

    @EntityGraph(value = "Dealer.cars")
    List<Dealer> findAll();

    @EntityGraph(value = "Dealer.cars")
    Optional<Dealer> findById(Long id);

    List<DealerName> findNamesBy();

    @EntityGraph(value = "Dealer.cars")
    List<Dealer> findByCarsNameOrCarsBrand(String name, String brand);
}
