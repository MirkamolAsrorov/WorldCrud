package com.example.repository;


import com.example.entity.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContinentRepository extends JpaRepository<Continent, Integer> {
    List<Continent> findAllByActiveTrue();

}
