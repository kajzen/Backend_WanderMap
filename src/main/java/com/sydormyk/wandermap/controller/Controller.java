package com.sydormyk.wandermap.controller;

import com.sydormyk.wandermap.dto.RegisterRequest;
import com.sydormyk.wandermap.model.City;
import com.sydormyk.wandermap.model.Country;
import com.sydormyk.wandermap.model.Place;
import com.sydormyk.wandermap.model.User;
import com.sydormyk.wandermap.service.AuthService;
import com.sydormyk.wandermap.service.CityService;
import com.sydormyk.wandermap.service.CountryService;
import com.sydormyk.wandermap.service.PlaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    private final CountryService countryService;
    private final CityService cityService;
    private final PlaceService placeService;
    private final AuthService authService;

    public Controller(CountryService countryService, CityService cityService, PlaceService placeService, AuthService authService) {
        this.countryService = countryService;
        this.cityService = cityService;
        this.placeService = placeService;
        this.authService = authService;
    }

    @GetMapping("/api/hello")
    public String index(){
        return "Hello wandermap!";
    }

    @GetMapping("/api/countries")
    public List<Country> getCountries(){
        return countryService.getAll();
    }

    @PostMapping("/api/countries")
    public void postCountries(@RequestBody Country country){
        countryService.save(country);
    }



    @GetMapping("/api/countries/{countryId}/cities")
    public List<City> getCities(@PathVariable Long countryId){
        return cityService.getCitiesByCountry(countryId);
    }

    @PostMapping("/api/countries/{countryId}/cities")
    public City postCities(@PathVariable Long countryId, @RequestBody City city){
        return cityService.saveToCountry(countryId, city);
    }

    @GetMapping("/api/cities/{cityId}/places")
    public List<Place> getPlaces(@PathVariable Long cityId){
        return placeService.getPlacesByCity(cityId);
    }

    @PostMapping("/api/cities/{cityId}/places")
    public Place postPlaces(@PathVariable Long cityId, @RequestBody Place place){
        return placeService.saveToCity(cityId, place);
    }

    @PostMapping("/api/auth/login")
    public String login(@RequestBody RegisterRequest  request){
        return authService.login(request.getEmail(), request.getPassword());
    }

    @PostMapping("/api/auth/register")
    public User registerUser(@RequestBody RegisterRequest  request){
        return authService.register(request.getEmail(), request.getPassword());
    }





}
