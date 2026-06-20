package com.sydormyk.wandermap.repository;

import com.sydormyk.wandermap.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country,Long> {
    List<Country> findByUserId(Long userId);

}
