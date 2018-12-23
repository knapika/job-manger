package com.example.javaserver.repositories;

import com.example.javaserver.entities.Offer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Integer> {
}
