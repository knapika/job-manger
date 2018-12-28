package com.example.javaserver.repositories;

import com.example.javaserver.dtos.CityStats;
import com.example.javaserver.entities.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
    Optional<Company> findByUrl(String url);


    @Query(value="SELECT c.location_city as city, COUNT(*) as count FROM companies as c GROUP BY c.location_city",
            nativeQuery = true)
    List<Object []> getCitiesStats();

    @Query(value="Select c.location_city as city, COUNT(*) as count FROM companies as c JOIN (SELECT o.companyid, o.technology FROM offers as o WHERE o.technology = :technology) as of " +
            "on c.companyid = of.companyid " +
            "GROUP by c.location_city",
           nativeQuery = true)
    List<Object[]> getCitiesStatsByTechnologies(@Param("technology") String technology);
}
