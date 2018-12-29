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


    @Query(value = "Select after_curr.technology,\n" +
            "\tAVG(\n" +
            "\tcase after_curr.salary_duration\n" +
            "    \tWHEN 'MONTH' then after_curr.salary\n" +
            "        WHEN 'YEAR' then after_curr.salary / 12\n" +
            "        WHEN 'DAY' then after_curr.salary * 21\n" +
            "    END\n" +
            "    ) as count\n" +
            "FROM\n" +
            "(\n" +
            "SELECT o.technology as technology, \n" +
            "\tcase o.salary_currency\n" +
            "    \twhen 'PLN' then (o.salary_from + o.salary_to) / 2\n" +
            "        when 'GBP' then (o.salary_from + o.salary_to) / 2 * 4.77\n" +
            "        when 'EUR' then (o.salary_from + o.salary_to) / 2 * 4.29\n" +
            "        when 'USD' then (o.salary_from + o.salary_to) / 2 * 3.76\n" +
            "    end as salary,\n" +
            "    o.salary_duration  \n" +
            "FROM offers as o JOIN companies as c ON o.companyid = c.companyid\n" +
            "WHERE c.location_city = :city AND O.salary_from is not null AND o.salary_to is not null AND o.technology IS NOT NULL\n" +
            ") as after_curr\n" +
            "GROUP BY after_curr.technology",
            nativeQuery = true)
    List<Object[]> getTechnologiesStatsByCity(@Param("city") String city);
}
