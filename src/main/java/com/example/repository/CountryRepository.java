package com.example.repository;

import com.example.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country,Integer> {
    List<Country> findAllByActiveTrue();
}
