package com.example.app_2.repository;

import com.example.app_2.model.Location;
import com.example.app_2.model.WeatherData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@RepositoryRestResource(collectionResourceRel = "weathers", path = "weathers")
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    Page<WeatherData> findByDateGreaterThanEqualAndLocation(@DateTimeFormat(pattern = "yyyy-MM-dd") Date date,  Location location, Pageable pageable);
}