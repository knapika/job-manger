package com.example.javaserver.repositories;

import com.example.javaserver.dtos.CategoryStats;
import com.example.javaserver.entities.Offer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Integer> {

    @Query(value = "SELECT o.category AS category, COUNT(*) as count FROM offers as o GROUP BY o.category",
            nativeQuery = true)
    List<Object[]> getCategoryStats();
}
