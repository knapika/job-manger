package com.example.javaserver.repositories;

import com.example.javaserver.entities.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
    Optional<Company> findByUrl(String url);
}
