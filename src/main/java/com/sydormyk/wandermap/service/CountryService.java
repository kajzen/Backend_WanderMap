package com.sydormyk.wandermap.service;

import com.sydormyk.wandermap.model.Country;
import com.sydormyk.wandermap.model.User;
import com.sydormyk.wandermap.repository.CountryRepository;
import com.sydormyk.wandermap.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final UserRepository userRepository;

    public CountryService(CountryRepository countryRepository, UserRepository userRepository) {
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
    }

    public Country save(Country country){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("User not found"));
        country.setUser(user);
        return countryRepository.save(country);
    }

    public List<Country> getAll(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return countryRepository.findByUserId(user.getId());
    }
}
