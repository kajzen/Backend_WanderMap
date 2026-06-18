package com.sydormyk.wandermap.service;

import com.sydormyk.wandermap.model.City;
import com.sydormyk.wandermap.model.Country;
import com.sydormyk.wandermap.repository.CityRepository;
import com.sydormyk.wandermap.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    public CityService(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }


    public City saveToCountry(Long countryId, City city) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new RuntimeException("Country not found"));
        city.setCountry(country);
        return cityRepository.save(city);
    }

    public List<City> getCitiesByCountry(Long countryId){
        return cityRepository.findByCountryId(countryId);
    }

}
