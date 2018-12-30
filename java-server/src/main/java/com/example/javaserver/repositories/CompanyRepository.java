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

    @Query(value="Select c.location_city as city, \n" +
            "\tCOUNT(*) as count,\n" +
            "\tAVG(\n" +
            "        case of.salary_duration\n" +
            "    \t\twhen 'MONTH' then of.salary\n" +
            "        \twhen 'YEAR' then of.salary / 12\n" +
            "        \twhen 'DAY' then of.salary * 21\n" +
            "        end\n" +
            "    ) as avg\n" +
            "    FROM companies as c \n" +
            "\t\tJOIN (SELECT o.companyid, o.technology, o.salary_duration,\n" +
            "      \t\tcase o.salary_currency\n" +
            "            \twhen 'PLN' then (o.salary_from + o.salary_to) / 2\n" +
            "            \twhen 'GBP' then (o.salary_from + o.salary_to) / 2 * 4.77\n" +
            "            \twhen 'EUR' then (o.salary_from + o.salary_to) / 2 * 4.29\n" +
            "            \twhen 'USD' then (o.salary_from + o.salary_to) / 2 * 3.76\n" +
            "      \t\tend as salary\n" +
            "      \t\tFROM offers as o WHERE o.technology LIKE :technology AND o.salary_from is not null) as of\n" +
            "on c.companyid = of.companyid\n" +
            "Group by c.location_city",
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
