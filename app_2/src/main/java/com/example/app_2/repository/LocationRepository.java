package com.example.app_2.repository;

import com.example.app_2.model.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "locations", path = "locations")
public interface LocationRepository extends JpaRepository<Location, Long> {
    Page<Location> findByCountry(String country, Pageable pageable);
    Optional<Location> findByCity(String city);
}