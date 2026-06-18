package com.sydormyk.wandermap.service;

import com.sydormyk.wandermap.model.City;
import com.sydormyk.wandermap.model.Place;
import com.sydormyk.wandermap.repository.CityRepository;
import com.sydormyk.wandermap.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final CityRepository cityRepository;

    public PlaceService(PlaceRepository placeRepository, CityRepository cityRepository) {
        this.placeRepository = placeRepository;
        this.cityRepository = cityRepository;
    }

    public Place saveToCity(Long cityId, Place place) {
        City city = cityRepository.findById(cityId)
            .orElseThrow(() -> new RuntimeException("City not found"));
        place.setCity(city);
        return placeRepository.save(place);
    }

    public List<Place> getPlacesByCity(Long cityId) {
        return  placeRepository.findByCityId(cityId);
    }
}
