package com.example.javaserver.repositories;

import com.example.javaserver.entities.ParserUsed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParserRepository extends CrudRepository<ParserUsed, Integer> {
    Optional<ParserUsed> findByParserName(String name);
}
