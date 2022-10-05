package com.example.repository;


import com.example.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region,Integer> {
    List<Region> findAllByActiveTrue();
    List<Region> findByCountry_Id(Integer id);

}
