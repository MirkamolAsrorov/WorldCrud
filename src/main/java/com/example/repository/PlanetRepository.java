package com.example.repository;


import com.example.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanetRepository extends JpaRepository<Planet,Integer> {
    List<Planet> findAllByActiveTrue();
    List<Planet> findByGalactic_Id(Integer id);

}
