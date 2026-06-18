package com.sydormyk.wandermap.service;

import com.sydormyk.wandermap.model.Country;
import com.sydormyk.wandermap.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country save(Country country){
        return countryRepository.save(country);
    }

    public List<Country> getAll(){
        return countryRepository.findAll();
    }
}
