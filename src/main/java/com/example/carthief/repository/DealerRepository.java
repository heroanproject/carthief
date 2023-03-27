package com.example.carthief.repository;

import com.example.carthief.entity.Dealer;
import com.example.carthief.projection.DealerName;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DealerRepository extends ListCrudRepository<Dealer,Long> {

    @EntityGraph(value = "Dealer.cars")
    List<Dealer> findAll();

    @EntityGraph(value = "Dealer.cars")
    Optional<Dealer> findById(Long id);

    List<DealerName> findNamesBy();

    @Query(value = "select * from dealer s where s.name like :keyword", nativeQuery = true)
    List<Dealer> findByKeyword(@Param("keyword") String keyword);
}
