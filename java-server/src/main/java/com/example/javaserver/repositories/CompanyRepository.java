package com.example.javaserver.repositories;

import com.example.javaserver.entities.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
    Optional<Company> findByUrl(String url);


    @Query(value="SELECT c.location_city as city, COUNT(*) as count FROM companies as c GROUP BY c.location_city",
            nativeQuery = true)
    List<Object []> getCitiesStats();
}
