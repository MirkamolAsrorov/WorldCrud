package com.example.repository;


import com.example.entity.Galactic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalacticRepository extends JpaRepository<Galactic,Integer> {
    List<Galactic> findAllByActiveTrue();

}
