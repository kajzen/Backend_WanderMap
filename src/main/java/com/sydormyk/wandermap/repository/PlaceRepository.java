package com.sydormyk.wandermap.repository;


import com.sydormyk.wandermap.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place,Long> {

    List<Place> findByCityId(Long cityId);
}
