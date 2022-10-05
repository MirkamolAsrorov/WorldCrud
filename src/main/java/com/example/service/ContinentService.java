package com.example.service;


import com.example.entity.Continent;
import com.example.entity.Planet;
import com.example.dto.ApiResponse;
import com.example.dto.ContinentDTO;
import com.example.repository.ContinentRepository;
import com.example.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContinentService {
    @Autowired
    PlanetRepository planetRepository;
    @Autowired
    ContinentRepository continentRepository;

    public ApiResponse add(ContinentDTO continentDTO) {
        Optional<Planet> optionalPlanet = planetRepository.findById(continentDTO.getPlanetId());
        if (optionalPlanet.isEmpty()) return new ApiResponse("The data don't have in database", false);
        Planet planet = optionalPlanet.get();

        Continent continent = new Continent();
        continent.setName(continentDTO.getName());
        continent.setPlanet(planet);

        Continent save = continentRepository.save(continent);
        return new ApiResponse("saved", true, save);
    }

    public ApiResponse edit(Integer id,ContinentDTO continentDTO) {
        Optional<Continent> optionalContinent = continentRepository.findById(id);
        Continent continent = optionalContinent.get();

        Optional<Planet> optionalPlanet = planetRepository.findById(continentDTO.getPlanetId());
        Planet planet = optionalPlanet.get();

        continent.setName(continentDTO.getName());
        continent.setPlanet(planet);

        continentRepository.save(continent);
        return new ApiResponse("Updated!", true);
    }
}
